package io.github.cloudstars.lowcode.object.editor.config.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.NumberValueTypeConfig;
import io.github.cloudstars.lowcode.object.commons.field.AbstractObjectFieldConfig;
import io.github.cloudstars.lowcode.object.commons.field.ObjectFieldConfigClass;

/**
 * 数字字段配置
 *
 * @author clouds
 */
@ObjectFieldConfigClass(name = "NUMBER")
public class NumberObjectFieldConfig extends AbstractObjectFieldConfig {

    public NumberObjectFieldConfig() {
    }

    public NumberObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new NumberValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
