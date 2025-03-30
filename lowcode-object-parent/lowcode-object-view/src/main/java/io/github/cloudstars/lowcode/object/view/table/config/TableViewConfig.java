package io.github.cloudstars.lowcode.object.view.table.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.config.AbstractViewConfig;
import io.github.cloudstars.lowcode.object.view.table.config.action.ActionsConfig;
import io.github.cloudstars.lowcode.object.view.table.config.query.QueryConfig;

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
    private QueryConfig query;

    /**
     * 操作配置
     */
    private ActionsConfig actions;

    public TableViewConfig() {
    }

    public TableViewConfig(JsonObject configJson) {
        super(configJson);

        Object queryValue = configJson.get("query");
        if (queryValue != null) {
            this.setQuery(new QueryConfig((JsonObject) queryValue));
        }

        Object actionsValue = configJson.get("actions");
        if (actionsValue != null) {
            this.setActions(new ActionsConfig((JsonObject) actionsValue));
        }
    }

    public QueryConfig getQuery() {
        return query;
    }

    public void setQuery(QueryConfig query) {
        this.query = query;
    }

    public ActionsConfig getActions() {
        return actions;
    }

    public void setActions(ActionsConfig actions) {
        this.actions = actions;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put("query", this.query.toJson());
        configJson.put("actions", this.actions.toJson());

        return configJson;
    }
}
