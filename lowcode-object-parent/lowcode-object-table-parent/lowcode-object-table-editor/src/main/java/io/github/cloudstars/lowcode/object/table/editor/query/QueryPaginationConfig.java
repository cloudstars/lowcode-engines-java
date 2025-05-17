package io.github.cloudstars.lowcode.object.table.editor.query;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 分页规则配置
 *
 * @author clouds
 */
public class QueryPaginationConfig extends AbstractConfig {

    public QueryPaginationConfig() {
    }

    public QueryPaginationConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}