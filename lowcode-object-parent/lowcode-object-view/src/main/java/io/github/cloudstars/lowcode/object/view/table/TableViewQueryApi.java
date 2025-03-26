package io.github.cloudstars.lowcode.object.view.table;

import io.github.cloudstars.lowcode.commons.data.valuetype.ValueTypeConfig;
import io.github.cloudstars.lowcode.object.view.ViewApi;

/**
 * 表格视图查询接口
 *
 * @author clouds
 */
public class TableViewQueryApi implements ViewApi {

    @Override
    public String getName() {
        return "TableView.Query";
    }

    @Override
    public ValueTypeConfig getInputDataType() {
        return null;
    }

    @Override
    public ValueTypeConfig getOutputDataType() {
        return null;
    }

    @Override
    public Object execute(Object data) {
        return null;
    }
}
