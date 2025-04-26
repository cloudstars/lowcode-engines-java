package io.github.cloudstars.lowcode.object.form.config.field;

import io.github.cloudstars.lowcode.commons.value.type.TextValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 文本字段配置
 *
 * @author clouds
 */
@FieldConfigClass(name = "TEXT")
public class TextFieldConfig extends AbstractFieldConfig {

    public TextFieldConfig() {
    }

    public TextFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new TextValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
