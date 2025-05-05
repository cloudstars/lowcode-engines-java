package io.github.cloudstars.lowcode.object.view.editor.table;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.AbstractObjectViewConfig;

/**
 * 模型表格类视图配置
 *
 * @author clouds
 */
public class AbstractObjectTableViewConfig extends AbstractObjectViewConfig {

    public AbstractObjectTableViewConfig() {
    }

    public AbstractObjectTableViewConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
