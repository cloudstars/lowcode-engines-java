package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.BooleanValueTypeConfig;

/**
 * 布尔字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "BOOLEAN")
public class BooleanBpmFieldConfig extends AbstractBpmFieldConfig<BooleanValueTypeConfig> {

    public BooleanBpmFieldConfig() {
    }

    public BooleanBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new BooleanValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
