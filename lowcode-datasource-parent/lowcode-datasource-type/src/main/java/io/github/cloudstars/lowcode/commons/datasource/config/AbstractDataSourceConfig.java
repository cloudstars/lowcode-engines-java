package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

/**
 * 抽象的数据源配置
 *
 * @author clouds
 */
public class AbstractDataSourceConfig<V extends XValueTypeConfig> extends AbstractTypedConfig implements XDataSourceConfig<V> {

    /**
     * 数据格式
     */
    private V valueType;

    public AbstractDataSourceConfig() {
    }

    public AbstractDataSourceConfig(JsonObject configJson) {
        super(configJson);

        ConfigUtils.consumeIfPresent(configJson, XValueTypeConfig.ATTR, (v) -> {
            this.valueType = (V) ValueTypeConfigFactory.newInstance((JsonObject) v);
        });
    }

    @Override
    public V getValueType() {
        return valueType;
    }

    public void setValueType(V valueType) {
        this.valueType = valueType;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putJsonObject(configJson, XValueTypeConfig.ATTR, this.valueType);

        return configJson;
    }

}
