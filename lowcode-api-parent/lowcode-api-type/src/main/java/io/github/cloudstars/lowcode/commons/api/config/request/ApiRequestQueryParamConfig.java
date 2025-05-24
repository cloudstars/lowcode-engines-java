package io.github.cloudstars.lowcode.commons.api.config.request;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.AbstractFieldConfig;

/**
 * API请求参数配置
 *
 * @author clouds
 */
public class ApiRequestQueryParamConfig extends AbstractFieldConfig {

    public ApiRequestQueryParamConfig() {
    }

    public ApiRequestQueryParamConfig(JsonObject configJson) {
        super(configJson);
    }
}
