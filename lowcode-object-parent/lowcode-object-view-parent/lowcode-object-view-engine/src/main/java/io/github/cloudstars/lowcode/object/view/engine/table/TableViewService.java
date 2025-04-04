package io.github.cloudstars.lowcode.object.view.engine.table;

import io.github.cloudstars.lowcode.object.view.engine.table.api.PageQueryOutput;
import io.github.cloudstars.lowcode.object.view.engine.table.api.TableViewPageQueryApiInput;
import io.github.cloudstars.lowcode.object.view.engine.table.api.TableViewQueryApiInput;

import java.util.List;
import java.util.Map;

/**
 * 表格视图服务接口
 *
 * @author clouds
 */
public interface TableViewService {

    /**
     * 执行表格视图查询接口
     *
     * @param viewKey
     * @param input
     * @return 列表数据
     */
    PageQueryOutput<Map<String, Object>> execute(String viewKey, TableViewPageQueryApiInput input);

    /**
     * 执行表格视图查询接口
     *
     * @param viewKey
     * @param input
     * @return 列表数据
     */
    List<Map<String, Object>> execute(String viewKey, TableViewQueryApiInput input);

}
