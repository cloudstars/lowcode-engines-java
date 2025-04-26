package io.github.cloudstars.lowcode.dynamic.value;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的默认值配置
 *
 * @author clouds
 */
public abstract class AbstractDynamicValueConfig extends AbstractTypedConfig implements XDynamicValueConfig {

    protected AbstractDynamicValueConfig() {
    }

    protected AbstractDynamicValueConfig(JsonObject configJson) {
        super(configJson);
    }

}
