package io.github.cloudstars.lowcode.object.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.bpm.form.BpmFormConfig;

/**
 * 子表单字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "SUBFORM")
public class SubFormBpmFieldConfig extends AbstractBpmFieldConfig {

    /**
     * 子表单配置
     */
    private BpmFormConfig subBpmFormConfig;

    public SubFormBpmFieldConfig() {
    }

    public SubFormBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.subBpmFormConfig = new BpmFormConfig(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();

        // 将子表单中的个性化配置项合并到当前字段配置中
        JsonObject<String, Object> subFormConfigJson = this.subBpmFormConfig.toJson();
        subFormConfigJson.forEach((k, v) -> {
            if (!configJson.containsKey(k)) {
                configJson.put(k, v);
            }
        });

        return configJson;
    }

}
