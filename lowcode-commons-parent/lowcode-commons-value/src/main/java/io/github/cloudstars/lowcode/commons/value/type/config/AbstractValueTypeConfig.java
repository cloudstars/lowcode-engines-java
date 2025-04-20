package io.github.cloudstars.lowcode.commons.value.type.config;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的数据格式配置
 *
 * @author clouds
 */
public abstract class AbstractValueTypeConfig extends AbstractTypedConfig implements XValueTypeConfig {

    public AbstractValueTypeConfig() {
    }

    public AbstractValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(ValueTypeConfigClass.class).name());
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
