package io.github.cloudstars.lowcode.commons.data.defaultvalue;

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

}
