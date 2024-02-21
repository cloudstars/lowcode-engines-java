package net.cf.api.engine.util.valid;


import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import net.cf.api.engine.exception.ApiDispatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * schema校验工具类
 * @author 80345746
 * @version v1.0
 * @date 2024/1/23 17:36
 */
public class ValidJsonSchemaHelper {
    private static final Logger log = LoggerFactory.getLogger(ValidJsonSchemaHelper.class);
    private static final char ROOT_PREFIX_CHAR = '/';

    private ValidJsonSchemaHelper() {
    }

    /**
     * 通过schema校验json字符串
     * @param schema  schema
     * @param jsonData json字符串
     * @return 校验结果
     */
    public static ValidResult validate(String schema, String jsonData) {
        if (!StringUtils.hasText(schema)) {
            log.info("请求schema配置为空，默认校验通过");
            ValidResult validResult = new ValidResult();
            validResult.setPassed(true);
            return validResult;
        }
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
        JsonNode dataNode;
        ProcessingReport report;
        try {
            JsonNode schemaNode = JsonLoader.fromString(schema);
            JsonNode required = schemaNode.get("required");
            if (null != required && !required.has(0)) {
                log.info("schema为[{}]，没有必输项，默认校验成功", schema);
                ValidResult validResult = new ValidResult();
                validResult.setPassed(true);
                return validResult;
            }
            JsonSchema jsonSchema = jsonSchemaFactory.getJsonSchema(schemaNode);
            dataNode = JsonLoader.fromString(jsonData);
            report = jsonSchema.validate(dataNode, true);
        } catch (IOException e) {
            log.warn("请求数据废json标准[{}]", jsonData, e);
            throw new ApiDispatchException("数据非json标准");
        } catch (ProcessingException e) {
            log.warn("schema校验执行异常，当前schema定义为[{}]", schema, e);
            throw new ApiDispatchException("schema校验异常");
        }
        return exchangeValidResult(report);
    }

    private static ValidResult exchangeValidResult(ProcessingReport report) {
        ValidResult validResult = new ValidResult();
        if (report.isSuccess()) {
            validResult.setPassed(true);
            return validResult;
        }
        for (ProcessingMessage next : report) {
            LogLevel logLevel = next.getLogLevel();
            if (logLevel != LogLevel.ERROR) {
                continue;
            }
            JsonNode jsonNode = next.asJson();
            String keyword = jsonNode.get("keyword").asText();
            if ("required".equalsIgnoreCase(keyword)) {
                List<JsonNode> missing = jsonNode.findValues("missing");
                JsonNode missList = missing.get(0);
                String parent = formatJsonNodeErrDesc(jsonNode, true);
                missList.forEach(e -> validResult.addErrorMsg(parent + e.asText() + "必填"));
            }
            if ("type".equalsIgnoreCase(keyword)) {
                String errorMsg = MessageFormat.format("{0}类型错误(期望：{1} 实际：{2})",
                        formatJsonNodeErrDesc(jsonNode, false),
                        prettyJsonTextArray(jsonNode.findValues("expected")),
                        jsonNode.get("found").asText());
                validResult.addErrorMsg(errorMsg);
            }
        }
        return validResult;
    }

    private static Object prettyJsonTextArray(List<JsonNode> list) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isEmpty(list) || list.get(0) == null) {
            return "";
        }
        JsonNode jsonNode = list.get(0);
        for (JsonNode node : jsonNode) {
            sb.append(node.asText()).append("/");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }


    private static String formatJsonNodeErrDesc(JsonNode reportJsonNode, boolean isParent) {
        String path = reportJsonNode.get("instance").get("pointer").asText();
        if (path != null && !path.isEmpty() && path.charAt(0) == ROOT_PREFIX_CHAR) {
            path = path.substring(1);
            path = isParent ? path + "." : path;
            return path.replace("/", ".");
        }
        return path;
    }
}
