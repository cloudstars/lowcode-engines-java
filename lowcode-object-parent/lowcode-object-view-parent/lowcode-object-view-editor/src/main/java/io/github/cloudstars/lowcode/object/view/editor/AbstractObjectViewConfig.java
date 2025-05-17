package io.github.cloudstars.lowcode.object.view.editor;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.AbstractObjectConfig;
import io.github.cloudstars.lowcode.object.commons.AbstractObjectResourceConfig;

/**
 * 抽象的视图配置
 *
 * @author clouds 
 */
public abstract class AbstractObjectViewConfig extends AbstractObjectResourceConfig implements XObjectViewConfig {

    public AbstractObjectViewConfig() {
    }

    public AbstractObjectViewConfig(JsonObject configJson) {
        super(configJson);
    }

    public AbstractObjectViewConfig(AbstractObjectConfig objectConfig) {

    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
