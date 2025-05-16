package io.github.cloudstars.lowcode.object.editor.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.AbstractObjectConfig;
import io.github.cloudstars.lowcode.object.commons.ObjectConfigClass;

/**
 * 基于表单的模型配置实现
 *
 * @author clouds
 */
@ObjectConfigClass(name = "OBJECT")
public class FormBasedObjectConfig extends AbstractObjectConfig {

    public FormBasedObjectConfig() {
    }

    public FormBasedObjectConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
