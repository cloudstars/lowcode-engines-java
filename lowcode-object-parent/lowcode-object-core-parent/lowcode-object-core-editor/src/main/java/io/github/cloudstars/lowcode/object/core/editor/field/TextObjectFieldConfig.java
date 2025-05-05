package io.github.cloudstars.lowcode.object.core.editor.field;

import io.github.cloudstars.lowcode.commons.value.type.TextValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 文本字段配置
 *
 * @author clouds
 */
@ObjectFieldConfigClass(name = "TEXT")
public class TextObjectFieldConfig extends AbstractObjectFieldConfig {

    public TextObjectFieldConfig() {
    }

    public TextObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new TextValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
