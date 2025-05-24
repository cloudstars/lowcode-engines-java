package io.github.cloudstars.lowcode.object.table.editor;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.table.editor.query.QueryConfig;
import io.github.cloudstars.lowcode.object.view.editor.AbstractObjectViewConfig;

/**
 * 模型表格类视图配置
 *
 * @author clouds
 */
public class AbstractObjectTableViewConfig extends AbstractObjectViewConfig {

    // 查询配置
    private static final String ATTR_QUERY = "query";

    /**
     * 查询配置
     */
    private QueryConfig query;

    public AbstractObjectTableViewConfig() {
    }

    public AbstractObjectTableViewConfig(JsonObject configJson) {
        super(configJson);

        this.query = ConfigUtils.getObject(configJson, ATTR_QUERY, (v) -> new QueryConfig(v));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, ATTR_QUERY, this.query);

        return configJson;
    }

}
