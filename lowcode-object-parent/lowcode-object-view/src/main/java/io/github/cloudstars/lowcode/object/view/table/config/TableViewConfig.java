package io.github.cloudstars.lowcode.object.view.table.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.config.AbstractViewConfig;
import io.github.cloudstars.lowcode.object.view.table.TableViewActionConfig;
import io.github.cloudstars.lowcode.object.view.table.config.query.QueryConfig;

import java.util.List;

/**
 * 表格视图配置
 *
 * @author clouds
 */
public class TableViewConfig extends AbstractViewConfig {

    /**
     * 表单视图配置的类型
     */
    public static String CONFIG_TYPE = "TABLE.VIEW";

    /**
     * 查询配置
     */
    private QueryConfig queryConfig;

    /**
     * 表头工具栏操作配置
     */
    private List<TableViewActionConfig> toolbarActions;

    /**
     * 行操作配置
     */
    private List<TableViewActionConfig> rowActions;

    public TableViewConfig() {
    }

    public TableViewConfig(JsonObject configJson) {
        super(configJson);

        Object queryConfig = configJson.get("query");
        if (queryConfig != null) {
            this.setQueryConfig(new QueryConfig((JsonObject) queryConfig));
        }
    }


    public QueryConfig getQueryConfig() {
        return queryConfig;
    }

    public void setQueryConfig(QueryConfig queryConfig) {
        this.queryConfig = queryConfig;
    }

    public List<TableViewActionConfig> getToolbarActions() {
        return toolbarActions;
    }

    public void setToolbarActions(List<TableViewActionConfig> toolbarActions) {
        this.toolbarActions = toolbarActions;
    }

    public List<TableViewActionConfig> getRowActions() {
        return rowActions;
    }

    public void setRowActions(List<TableViewActionConfig> rowActions) {
        this.rowActions = rowActions;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put("query", this.queryConfig.toJson());
        return configJson;
    }
}
