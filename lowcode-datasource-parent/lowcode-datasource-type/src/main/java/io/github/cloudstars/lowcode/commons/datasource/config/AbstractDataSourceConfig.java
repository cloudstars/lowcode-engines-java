package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 抽象的数据源配置
 *
 * @author clouds
 */
public class AbstractDataSourceConfig<T extends XValueTypeConfig> extends AbstractTypedConfig implements XDataSourceConfig {

    /**
     * 数据格式
     */
    private T valueType;

    public AbstractDataSourceConfig() {
    }

    public AbstractDataSourceConfig(JsonObject configJson) {
        super(configJson);

        ConfigUtils.consumeIfPresent(configJson, XValueTypeConfig.ATTR, (v) -> {
            this.valueType = (T) ValueTypeConfigFactory.newInstance((JsonObject) v);
        });
    }

    @Override
    public T getValueType() {
        return valueType;
    }

    public void setValueType(T valueType) {
        this.valueType = valueType;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putJsonIfNotNull(configJson, XValueTypeConfig.ATTR, this.valueType);

        return configJson;
    }

}
