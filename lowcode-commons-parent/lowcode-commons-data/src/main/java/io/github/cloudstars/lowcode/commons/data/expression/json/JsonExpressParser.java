package io.github.cloudstars.lowcode.commons.data.expression.json;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

public class JsonExpressParser implements XConfigParser<JsonExpression> {

    @Override
    public JsonExpression fromJson(JsonObject configJson) {
        String type = (String) configJson.get(XConfig.ATTR_TYPE);
        if (JsonExpression.TYPE_LOGIC.equalsIgnoreCase(type)) {
            return new LogicJsonExpression(configJson);
        } if (JsonExpression.TYPE_BINARY.equalsIgnoreCase(type)) {
            return new BinaryJsonExpression(configJson);
        } else {
            throw new RuntimeException("表达式类型[" + type + "]不支持");
        }
    }

    /**
     * 解析逻辑操作表达式
     *
     * @param configJson
     * @return
     */
    private LogicJsonExpression parseLogic(JsonObject configJson) {
        return null;
    }

    /**
     * 解析二元操作表达式
     *
     * @param configJson
     * @return
     */
    private BinaryJsonExpression parseBinary(JsonObject configJson) {
        return null;
    }

}
