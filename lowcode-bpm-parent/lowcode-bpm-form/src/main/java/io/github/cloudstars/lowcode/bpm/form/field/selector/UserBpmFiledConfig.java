package io.github.cloudstars.lowcode.bpm.form.field.selector;

import io.github.cloudstars.lowcode.bpm.form.field.BpmFieldConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ObjectValueTypeConfig;

/**
 * 用户选择字段类型
 *
 * @author clouds
 *
 */
@BpmFieldConfigClass(name = "USER")
public class UserBpmFiledConfig extends AbstractDataSourceSupportedBpmFieldConfig<ObjectValueTypeConfig> {

    public UserBpmFiledConfig() {
    }

    public UserBpmFiledConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new ObjectValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
