package io.github.cloudstars.lowcode.object.view.table;

import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.table.config.TableViewConfig;

/**
 * 表格视图配置解析器
 *
 * @author clouds
 */
@Deprecated
public class TableViewConfigParser implements XConfigParser<TableViewConfig> {

    @Override
    public TableViewConfig fromJson(JsonObject configJson) {
        TableViewConfig viewConfig = new TableViewConfig(configJson);
        return viewConfig;
    }

}
