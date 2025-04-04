package io.github.cloudstars.lowcode.object.view.commons.config.table.action;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 操作配置
 *
 * @author clouds
 */
public class ActionsConfig extends AbstractConfig {

    /**
     * 表头工具栏操作配置
     */
    private List<TableViewActionConfig> toolbar;

    /**
     * 行操作配置
     */
    private List<TableViewActionConfig> row;


    public ActionsConfig() {
    }

    public ActionsConfig(JsonObject configJson) {
        super(configJson);

        Object toolbarValue = configJson.get("toolbar");
        if (toolbarValue != null) {
            this.setToolbar(XConfigUtils.toList((JsonArray) toolbarValue, TableViewActionConfig.class));
        }

        Object rowValue = configJson.get("row");
        if (rowValue != null) {
            this.setRow(XConfigUtils.toList((JsonArray) rowValue, TableViewActionConfig.class));
        }
    }

    public List<TableViewActionConfig> getToolbar() {
        return toolbar;
    }

    public void setToolbar(List<TableViewActionConfig> toolbar) {
        this.toolbar = toolbar;
    }

    public List<TableViewActionConfig> getRow() {
        return row;
    }

    public void setRow(List<TableViewActionConfig> row) {
        this.row = row;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        if (this.toolbar != null) {
            configJson.put("toolbar", XConfigUtils.toJsonArray(this.toolbar));
        }
        if (this.row != null) {
            configJson.put("row", XConfigUtils.toJsonArray(this.row));
        }

        return configJson;
    }

}
