package io.github.cloudstars.lowcode.object.editor.spec1;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.field.AbstractObjectFieldConfig;

/**
 * 原样输出的字段类型配置
 *
 * @author clouds
 *
 */
public class OtherObjectFieldConfig extends AbstractObjectFieldConfig {

    private JsonObject configJson;

    public OtherObjectFieldConfig(JsonObject configJson) {
        this.configJson = configJson;
    }

    public JsonObject getConfigJson() {
        return configJson;
    }

    public void setConfigJson(JsonObject configJson) {
        this.configJson = configJson;
    }

    @Override
    public JsonObject toJson() {
        return this.configJson;
    }

}
