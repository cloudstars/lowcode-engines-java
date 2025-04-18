package io.github.cloudstars.lowcode.form.commons.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数字字段配置
 *
 * @author clouds
 */
@FieldConfigClass(type = "NUMBER")
public class NumberFieldConfig extends AbstractFieldConfig {

    public NumberFieldConfig() {
    }

    public NumberFieldConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
