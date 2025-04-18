package io.github.cloudstars.lowcode.form.commons.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 文本字段配置
 *
 * @author clouds
 */
@FieldConfigClass(type = "TEXT")
public class TextFieldConfig extends AbstractFieldConfig {

    public TextFieldConfig() {
    }

    public TextFieldConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
