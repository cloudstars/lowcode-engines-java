package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 其它字段配置（不能作为条件、不能编辑）
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "OTHER")
public class OtherBpmFieldConfig extends AbstractBpmFieldConfig {

    public OtherBpmFieldConfig() {
    }

    public OtherBpmFieldConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
