package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.data.type.DataTypeConfig;
import io.github.cloudstars.lowcode.commons.data.type.DataTypeFactory;
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
        DataTypeConfig input = DataTypeFactory.newInstance(inputJson);
        JsonObject outputJson = (JsonObject) configJson.get("output");
        DataTypeConfig output = null;
        if (outputJson != null) {
            output = DataTypeFactory.newInstance(outputJson);
        }

        return new ApiConfig(input, output);
    }

}
