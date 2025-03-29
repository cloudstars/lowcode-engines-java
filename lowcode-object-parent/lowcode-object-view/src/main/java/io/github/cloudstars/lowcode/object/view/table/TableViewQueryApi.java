package io.github.cloudstars.lowcode.object.view.table;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.object.view.ObjectViewApi;

/**
 * 表格视图查询接口
 *
 * @author clouds
 */
public class TableViewQueryApi implements ObjectViewApi {

    @Override
    public String getName() {
        return "TableView.Query";
    }

    @Override
    public ApiConfig getApiConfig() {
        return null;
    }

    @Override
    public Object execute(Object data) {
        return null;
    }
}
