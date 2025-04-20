package io.github.cloudstars.lowcode.commons.data.predicate;

import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 表达式解析器
 *
 * @author clouds
 */
public class ExpressionConfigParser implements XConfigParser<XExpressionConfig> {

    @Override
    public XExpressionConfig fromJson(JsonObject configJson) {
        Object typeValue = configJson.get(XTypedConfig.ATTR);
        if (typeValue == null) {
            throw new RuntimeException("表达式配置中type不能为空，请检查您的配置：" + configJson);
        }

        return ExpressionConfigFactory.newInstance(configJson);
    }

}
