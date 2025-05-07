package io.github.cloudstars.lowcode.bpm.form.field.selector;

import io.github.cloudstars.lowcode.bpm.form.field.BpmFieldConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ObjectValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.OrgValueTypeConfig;

/**
 * 机构选择字段类型
 *
 * @author clouds
 *
 */
@BpmFieldConfigClass(name = "ORG")
public class OrgBpmFiledConfig extends AbstractSelectableSupportedBpmFieldConfig<OrgValueTypeConfig> {

    public OrgBpmFiledConfig() {
    }

    public OrgBpmFiledConfig(JsonObject configJson) {
        super(configJson);

        this.setTargetValueType(new ObjectValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
