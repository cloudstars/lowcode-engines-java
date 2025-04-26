package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.TimeValueTypeConfig;

/**
 * 时间字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "TIME")
public class TimeBpmFieldConfig extends AbstractBpmFieldConfig<TimeValueTypeConfig> {

    public TimeBpmFieldConfig() {
    }

    public TimeBpmFieldConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
