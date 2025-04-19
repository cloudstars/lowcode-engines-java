package io.github.cloudstars.lowcode.commons.data.datasource;

import java.util.List;

/**
 * 返回列表的数据源
 *
 * @param <D> 列表项的类型
 */
public interface XListDataSource<D extends Object> extends XDataSource<List<D>> {
}
