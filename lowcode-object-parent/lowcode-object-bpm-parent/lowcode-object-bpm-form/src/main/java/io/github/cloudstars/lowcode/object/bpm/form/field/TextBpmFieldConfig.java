package io.github.cloudstars.lowcode.object.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.TextValueTypeConfig;

/**
 * 文本字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "TEXT")
public class TextBpmFieldConfig extends AbstractBpmFieldConfig {

    public TextBpmFieldConfig() {
    }

    public TextBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new TextValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
