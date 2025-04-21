package io.github.cloudstars.lowcode.object.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.config.NumberValueTypeConfig;

/**
 * 数字字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "NUMBER")
public class NumberBpmFieldConfig extends AbstractBpmFieldConfig {

    public NumberBpmFieldConfig() {
    }

    public NumberBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new NumberValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
