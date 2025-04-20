package io.github.cloudstars.lowcode.commons.data.valuetype.config;

import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数据类型配置解析器
 *
 * @author clouds
 */
public class ValueTypeConfigParser implements XConfigParser<XValueTypeConfig> {

    @Override
    public XValueTypeConfig fromJson(JsonObject configJson) {
        Object dataTypeValue = configJson.get("type");
        if (dataTypeValue == null) {
            throw new RuntimeException("数据格式定义中type不能为空，请检查您的配置：" + configJson);
        }

        return ValueTypeConfigFactory.newInstance(configJson);
    }

}
