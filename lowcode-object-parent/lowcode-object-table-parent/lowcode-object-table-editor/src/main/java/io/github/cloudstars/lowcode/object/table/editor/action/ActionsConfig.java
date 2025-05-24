package io.github.cloudstars.lowcode.object.table.editor.action;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 操作配置
 *
 * @author clouds
 */
public class ActionsConfig extends AbstractConfig {

    // 工具栏配置名称
    private static final String ATTR_TOOLBAR = "toolbar";

    // 表格行配置名称
    private static final String ATTR_ROW = "row";

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

        this.toolbar = ConfigUtils.getList(configJson, ATTR_TOOLBAR, (v) -> new TableViewActionConfig(v));
        this.row = ConfigUtils.getList(configJson, ATTR_ROW, (v) -> new TableViewActionConfig(v));
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
        ConfigUtils.putJsonArray(configJson, ATTR_TOOLBAR, this.toolbar);
        ConfigUtils.putJsonArray(configJson, ATTR_ROW, this.row);

        return configJson;
    }

}
