package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.commons.datasource.config.AbstractDataSourceConfig;
import io.github.cloudstars.lowcode.value.loader.AbstractValueType;
import io.github.cloudstars.lowcode.value.loader.ValueTypeFactory;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;


public abstract class AbstractDataSourceLoader<S extends AbstractDataSourceConfig<C>, V extends AbstractValueType<C, D>, C extends XValueTypeConfig, P extends Object, D extends Object> implements XDataSourceLoader<S, V, C, P, D> {

    protected S dataSourceConfig;

    protected V valueType;

    public AbstractDataSourceLoader(S dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
        C valueTypeConfig = this.dataSourceConfig.getValueType();
        if (valueTypeConfig != null) {
            this.valueType = (V) ValueTypeFactory.newInstance(valueTypeConfig);
        }
    }

    @Override
    public S getConfig() {
        return this.dataSourceConfig;
    }

    @Override
    public V getValueType() {
        return this.valueType;
    }
}
