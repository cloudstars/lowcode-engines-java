package io.github.cloudstars.lowcode.bpm.form.field.selector;

import io.github.cloudstars.lowcode.bpm.form.field.BpmFieldConfigClass;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.OptionValueTypeConfig;

/**
 * 选项字段类型
 *
 * @author clouds
 *
 */
@BpmFieldConfigClass(name = "OPTION")
public class OptionBpmFiledConfig extends AbstractSelectableSupportedBpmFieldConfig<OptionValueTypeConfig> {

    // 标签配置名称
    private static final String ATTR_LABEL_FIELD = "labelField";
    // 值配置名称
    private static final String ATTR_VALUE_FIELD = "valueField";

    /**
     * 标签字段名称
     */
    private String labelField;

    /**
     * 值字段名称
     */
    private String valueField;

    public OptionBpmFiledConfig() {
    }

    public OptionBpmFiledConfig(JsonObject configJson) {
        super(configJson);

        this.labelField = (String) configJson.get(GlobalAttrNames.ATTR_LABEL_FIELD);
        this.valueField = (String) configJson.get(GlobalAttrNames.ATTR_VALUE_FIELD);
        this.setTargetValueType(new OptionValueTypeConfig(configJson));
    }

    public String getLabelField() {
        return labelField;
    }

    public void setLabelField(String labelField) {
        this.labelField = labelField;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, ATTR_LABEL_FIELD, this.labelField);
        ConfigUtils.put(configJson, ATTR_VALUE_FIELD, this.valueField);

        return configJson;
    }
}
