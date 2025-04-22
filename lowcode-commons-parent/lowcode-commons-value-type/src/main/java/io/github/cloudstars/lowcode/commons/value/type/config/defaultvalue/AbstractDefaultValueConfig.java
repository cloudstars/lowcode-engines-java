package io.github.cloudstars.lowcode.commons.value.type.config.defaultvalue;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的默认值配置
 *
 * @author clouds
 */
public abstract class AbstractDefaultValueConfig extends AbstractTypedConfig implements XDefaultValueConfig {

    protected AbstractDefaultValueConfig() {
    }

    protected AbstractDefaultValueConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}


