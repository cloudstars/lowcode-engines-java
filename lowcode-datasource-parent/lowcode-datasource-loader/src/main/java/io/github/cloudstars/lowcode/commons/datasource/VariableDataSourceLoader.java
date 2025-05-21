
package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.commons.datasource.config.VariableDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectPropertyUtils;
import io.github.cloudstars.lowcode.commons.value.AbstractValueType;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 变量数据源加载器
 *
 * @param <V> 数据格式类型
 * @param <C> 数据格式配置类型
 * @param <D> 数据类型
 */
@DataSourceLoaderClass(type = "VARIABLE", dataSourceConfigClass = VariableDataSourceConfig.class)
public class VariableDataSourceLoader<V extends AbstractValueType<C, D>, C extends XValueTypeConfig, D extends Object> extends AbstractDataSourceLoader<VariableDataSourceConfig<C>, V, C, Object, D> {

    public VariableDataSourceLoader(VariableDataSourceConfig dataSourceConfig) {
        super(dataSourceConfig);
    }

    @Override
    public D loadData(Object param) {
        String variable = this.dataSourceConfig.getVariable();
        Object rawData = ObjectPropertyUtils.getPropertyValue(param, variable);
        if (this.valueType != null) {
            D targetData = this.valueType.parseValue(rawData);
            return targetData;
        }

        return (D) rawData;
    }
}
