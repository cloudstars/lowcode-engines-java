package io.github.cloudstars.lowcode.form.commons.field;

import io.github.cloudstars.lowcode.commons.data.field.AbstractFieldConfig;
import io.github.cloudstars.lowcode.commons.data.field.FieldConfigClass;
import io.github.cloudstars.lowcode.commons.data.valuetype.TextValueTypeConfig;
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
