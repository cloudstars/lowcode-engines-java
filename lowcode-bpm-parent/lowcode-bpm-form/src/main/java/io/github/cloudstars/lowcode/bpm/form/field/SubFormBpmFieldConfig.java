package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.bpm.form.BpmFormConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ArrayValueTypeConfig;

/**
 * 子表单字段配置
 *
 * @author clouds
 */
@BpmFieldConfigClass(name = "SUBFORM")
public class SubFormBpmFieldConfig extends AbstractBpmFieldConfig<ArrayValueTypeConfig> {

    // 是否允许新增行配置名称
    private static final String ATTR_ALLOW_NEWLINE = "allowNewline";

    /**
     * 子表单配置
     */
    private BpmFormConfig subBpmFormConfig;

    /**
     * 是否允许新增行
     */
    private Boolean allowNewLine;

    public SubFormBpmFieldConfig() {
    }

    public SubFormBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.subBpmFormConfig = new BpmFormConfig(configJson);
        this.allowNewLine = (Boolean) configJson.get(ATTR_ALLOW_NEWLINE);
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
        ConfigUtils.putIfNotNull(configJson, ATTR_ALLOW_NEWLINE, this.allowNewLine);

        return configJson;
    }

}
