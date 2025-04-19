package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.data.valuetype.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * API配置解释器
 *
 * @author clouds
 */
public class ApiConfigParser implements XConfigParser<ApiConfig> {

    @Override
    public ApiConfig fromJson(JsonObject configJson) {
        JsonObject inputJson = (JsonObject) configJson.get("input");
        XValueTypeConfig input = ValueTypeConfigFactory.newInstance(inputJson);
        JsonObject outputJson = (JsonObject) configJson.get("output");
        XValueTypeConfig output = null;
        if (outputJson != null) {
            output = ValueTypeConfigFactory.newInstance(outputJson);
        }

        return new ApiConfig(input, output);
    }

}
