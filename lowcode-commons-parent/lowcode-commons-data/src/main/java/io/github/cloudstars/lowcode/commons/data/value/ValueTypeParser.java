package io.github.cloudstars.lowcode.commons.data.value;

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
        Object dataTypeValue = configJson.get("type");
        if (dataTypeValue == null) {
            throw new RuntimeException("数据格式定义中type不能为空，请检查您的配置：" + configJson);
        }

        return ValueTypeConfigFactory.newInstance(configJson);
    }

}
