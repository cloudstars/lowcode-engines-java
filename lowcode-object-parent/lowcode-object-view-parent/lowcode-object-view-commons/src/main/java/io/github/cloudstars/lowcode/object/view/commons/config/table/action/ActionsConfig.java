package io.github.cloudstars.lowcode.object.view.commons.config.table.action;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
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

        Object toolbarValue = configJson.get(ATTR_TOOLBAR);
        if (toolbarValue != null) {
            this.setToolbar(ConfigUtils.toList((JsonArray) toolbarValue, TableViewActionConfig.class));
        }

        Object rowValue = configJson.get(ATTR_ROW);
        if (rowValue != null) {
            this.setRow(ConfigUtils.toList((JsonArray) rowValue, TableViewActionConfig.class));
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
        ConfigUtils.putArrayIfNotNull(configJson, ATTR_TOOLBAR, this.toolbar);
        ConfigUtils.putArrayIfNotNull(configJson, ATTR_ROW, this.row);

        return configJson;
    }

}
