package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数据类型解析器
 *
 * @author clouds
 */
public class ValueTypeParser implements XConfigParser<ValueTypeConfig> {

    @Override
    public ValueTypeConfig fromJson(JsonObject configJson) {
        Object dataTypeValue = configJson.get("dataType");
        if (dataTypeValue == null) {
            throw new RuntimeException("数据类型定义中dataType不能为空，请检查您的配置：" + configJson);
        }

        ValueTypeConfig valueType = ValueTypeFactory.newInstance(configJson);
        return valueType;
    }

}
