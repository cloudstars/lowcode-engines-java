package io.github.cloudstars.lowcode.commons.data.defaultvalue;

import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 默认值解析器
 *
 * @author clouds
 */
public class DefaultValueConfigParser implements XConfigParser<XDefaultValueConfig> {

    @Override
    public XDefaultValueConfig fromJson(JsonObject configJson) {
        Object typeValue = configJson.get("type");
        if (typeValue == null) {
            throw new RuntimeException("默认值配置中type不能为空，请检查您的配置：" + configJson);
        }

        return DefaultValueConfigFactory.newInstance(configJson);
    }

}
