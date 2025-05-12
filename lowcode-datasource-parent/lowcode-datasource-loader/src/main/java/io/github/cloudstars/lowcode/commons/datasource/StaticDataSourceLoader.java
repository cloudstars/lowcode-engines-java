package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.commons.datasource.config.StaticDataSourceConfig;
import io.github.cloudstars.lowcode.commons.value.AbstractValueType;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 静态数据源加载器
 *
 * @param <V> 数据格式类型
 * @param <C> 数据格式配置类型
 * @param <D> 数据类型
 */
@DataSourceLoaderClass(type = "Static", dataSourceConfigClass = StaticDataSourceConfig.class)
public class StaticDataSourceLoader<V extends AbstractValueType<C, D>, C extends XValueTypeConfig, D extends Object> extends AbstractDataSourceLoader<StaticDataSourceConfig<C>, V, C, D> {

    public StaticDataSourceLoader(StaticDataSourceConfig dataSourceConfig) {
        super(dataSourceConfig);
    }

    @Override
    public D loadData() {
        Object rawData = this.dataSourceConfig.getData();
        if (this.valueType != null) {
            D targetData = this.valueType.parseValue(rawData);
            return targetData;
        }

        return (D) rawData;
    }
}
