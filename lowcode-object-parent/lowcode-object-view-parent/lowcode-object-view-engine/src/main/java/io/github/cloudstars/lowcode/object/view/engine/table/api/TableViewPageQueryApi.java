package io.github.cloudstars.lowcode.object.view.engine.table.api;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.object.view.engine.ObjectViewApi;

import java.util.Map;

/**
 * 表格视图查询接口
 *
 * @author clouds
 */
public class TableViewPageQueryApi implements ObjectViewApi<TableViewPageQueryApiInput, PageQueryOutput<Map<String, Object>>> {

    @Override
    public String getName() {
        return "TableView.Query";
    }

    @Override
    public ApiConfig getApiConfig() {
        return null;
    }

    @Override
    public PageQueryOutput<Map<String, Object>> execute(TableViewPageQueryApiInput input) {
        return null;
    }

}
