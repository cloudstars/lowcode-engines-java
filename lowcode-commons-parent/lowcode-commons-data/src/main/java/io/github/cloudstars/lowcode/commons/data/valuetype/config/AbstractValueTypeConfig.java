package io.github.cloudstars.lowcode.commons.data.valuetype.config;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的数据格式配置
 *
 * @param <T> 数据格式的值的类型
 * @author clouds
 */
public abstract class AbstractValueTypeConfig<T> extends AbstractTypedConfig implements XValueTypeConfig<T> {

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
