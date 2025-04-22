package io.github.cloudstars.lowcode.commons.value.dynamic;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的默认值配置
 *
 * @author clouds
 */
public abstract class AbstractValueConfig extends AbstractTypedConfig implements XValueConfig {

    protected AbstractValueConfig() {
    }

    protected AbstractValueConfig(JsonObject configJson) {
        super(configJson);
    }

}
