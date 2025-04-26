package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.DateValueTypeConfig;

/**
 * 日期字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "DATE")
public class DateBpmFieldConfig extends AbstractBpmFieldConfig<DateValueTypeConfig> {

    public DateBpmFieldConfig() {
    }

    public DateBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new DateValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
