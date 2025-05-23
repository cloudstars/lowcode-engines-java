package io.github.cloudstars.lowcode.object.table.editor.query;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 排序规则配置
 *
 * @author clouds
 */
public class QueryOrderConfig extends AbstractConfig {

    public QueryOrderConfig() {
        super();
    }

    public QueryOrderConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}