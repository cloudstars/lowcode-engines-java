package io.github.cloudstars.lowcode.commons.datasource;

/**
 * 返回对象的数据源
 *
 * @param <D> 对象的类型
 */
public interface XDataSource<D extends Object> {

    /**
     * 获取数据
     *
     * @return
     */
    D getData();
}
