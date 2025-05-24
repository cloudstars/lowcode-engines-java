package io.github.cloudstars.lowcode.bpm.form.field.selector;

import io.github.cloudstars.lowcode.bpm.form.field.BpmFieldConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.UserValueTypeConfig;

/**
 * 用户选择字段类型
 *
 * @author clouds
 *
 */
@BpmFieldConfigClass(name = "USER")
public class UserBpmFiledConfig extends AbstractSelectableSupportedBpmFieldConfig<UserValueTypeConfig> {

    public UserBpmFiledConfig() {
    }

    public UserBpmFiledConfig(JsonObject configJson) {
        super(configJson);

        this.setTargetValueType(new UserValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
