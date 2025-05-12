package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;
import io.github.cloudstars.lowcode.commons.value.XValueType;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 数据源加载器
 *
 * @param <S> 数据源配置类型
 * @param <V> 数据格式类型
 * @param <C> 数据格式配置类型
 * @param <D> 加载返回的数据类型
 */
public interface XDataSourceLoader<S extends XDataSourceConfig<C>, V extends XValueType<C, D>, C extends XValueTypeConfig, D extends Object> {

    /**
     * 获取数据源配置
     *
     * @return 数据源配置
     */
    S getConfig();

    /**
     * 获取数据格式
     *
     * @return
     */
    V getValueType();

    /**
     * 加载数据
     *
     * @return 数据
     */
    D loadData();
}
