package io.github.cloudstars.lowcode.object.form.config.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.form.config.FormConfig;

/**
 * 子表单字段配置
 *
 * @author clouds
 */
@FieldConfigClass(name = "SUBFORM")
public class SubFormFieldConfig extends AbstractFieldConfig {

    /**
     * 子表单配置
     */
    private FormConfig subFormConfig;

    public SubFormFieldConfig() {
    }

    public SubFormFieldConfig(JsonObject configJson) {
        super(configJson);

        this.subFormConfig = new FormConfig(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();

        // 将子表单中的个性化配置项合并到当前字段配置中
        JsonObject<String, Object> subFormConfigJson = this.subFormConfig.toJson();
        subFormConfigJson.forEach((k, v) -> {
            if (!configJson.containsKey(k)) {
                configJson.put(k, v);
            }
        });

        return configJson;
    }

}
