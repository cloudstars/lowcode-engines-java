package io.github.cloudstars.lowcode.object.commons;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 基于表单的模型配置实现
 *
 * @author clouds
 */
@ObjectConfigClass(name = "OBJECT")
public class FormBasedObjectConfig extends AbstractObjectConfig {

    public FormBasedObjectConfig() {
        this.setType("OBJECT");
    }

    public FormBasedObjectConfig(JsonObject configJson) {
        super(configJson);
        this.setType("OBJECT");
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
