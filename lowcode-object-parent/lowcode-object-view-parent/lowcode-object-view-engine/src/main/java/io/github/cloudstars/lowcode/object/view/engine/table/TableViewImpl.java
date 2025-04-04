package io.github.cloudstars.lowcode.object.view.engine.table;

import io.github.cloudstars.lowcode.object.view.commons.config.table.TableViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.table.api.*;

import java.util.List;
import java.util.Map;

/**
 * 模型表格视图实现
 *
 * @author clouds 
 */
public class TableViewImpl implements TableView<TableViewConfig> {

    @Override
    public List<Map<String, Object>> execute(TableViewQueryApiInput input) {
        TableViewQueryApi api = this.loadTableViewQueryApi();
        return api.execute(input);
    }

    /**
     * 加载表格视图查询接口
     *
     * @return
     */
    private TableViewQueryApi loadTableViewQueryApi() {
        return null;
    }

    @Override
    public PageQueryOutput<Map<String, Object>> execute(TableViewPageQueryApiInput input) {
        TableViewPageQueryApi api = this.loadTableViewPageQueryApi();
        return api.execute(input);
    }


    /**
     * 加载表格视图分页查询接口
     *
     * @return
     */
    private TableViewPageQueryApi loadTableViewPageQueryApi() {
        return null;
    }

}
