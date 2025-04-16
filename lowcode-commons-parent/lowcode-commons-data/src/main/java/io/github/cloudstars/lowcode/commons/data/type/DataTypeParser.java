package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数据类型解析器
 *
 * @author clouds
 */
public class DataTypeParser implements XConfigParser<ValueTypeConfig> {

    @Override
    public ValueTypeConfig fromJson(JsonObject configJson) {
        Object dataTypeValue = configJson.get("dataType");
        if (dataTypeValue == null) {
            throw new RuntimeException("数据类型定义中dataType不能为空，请检查您的配置：" + configJson);
        }

        return ValueTypeConfigFactory.newInstance(configJson);
    }

}
