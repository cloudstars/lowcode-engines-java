package io.github.cloudstars.lowcode.object.editor.config.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.field.ObjectFieldConfigClass;
import io.github.cloudstars.lowcode.object.commons.field.objectref.AbstractObjectRefObjectFieldConfig;
import io.github.cloudstars.lowcode.object.editor.config.FormBasedObjectConfig;

/**
 * 子模型字段配置
 *
 * @author clouds
 */
@ObjectFieldConfigClass(name = "DETAIL-OBJECT")
public class DetailObjectObjectFieldConfig extends AbstractObjectRefObjectFieldConfig {

    /**
     * 子模型配置
     */
    private FormBasedObjectConfig subObjectConfig;

    public DetailObjectObjectFieldConfig() {
    }

    public DetailObjectObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        this.subObjectConfig = new FormBasedObjectConfig(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();

        // 将子模型中的个性化配置项合并到当前字段配置中
        JsonObject<String, Object> subFormConfigJson = this.subObjectConfig.toJson();
        subFormConfigJson.forEach((k, v) -> {
            if (!configJson.containsKey(k)) {
                configJson.put(k, v);
            }
        });

        return configJson;
    }

}
