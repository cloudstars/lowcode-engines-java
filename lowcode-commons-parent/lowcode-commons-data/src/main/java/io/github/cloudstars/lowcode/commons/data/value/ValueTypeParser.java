package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.commons.editor.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数值类型解析器
 *
 * @author clouds
 */
public class ValueTypeParser implements XConfigParser<ValueTypeConfig> {

    @Override
    public ValueTypeConfig fromJson(JsonObject configJson) {
        Object dataTypeValue = configJson.get("dataType");
        if (dataTypeValue == null) {
            throw new RuntimeException("数值类型定义中dataType不能为空，请检查您的配置：" + configJson);
        }

        ValueTypeConfig valueType = ValueTypeFactory.newInstance(configJson);
        return valueType;
    }

}
