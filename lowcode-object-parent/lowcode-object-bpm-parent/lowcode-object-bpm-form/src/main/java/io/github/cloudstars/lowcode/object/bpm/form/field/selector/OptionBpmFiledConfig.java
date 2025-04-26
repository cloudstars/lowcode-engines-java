package io.github.cloudstars.lowcode.object.bpm.form.field.selector;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.OptionValueTypeConfig;
import io.github.cloudstars.lowcode.object.bpm.form.field.BpmFieldConfigClass;

/**
 * 选项字段类型
 *
 * @author clouds
 *
 */
@BpmFieldConfigClass(name = "OPTION")
public class OptionBpmFiledConfig extends AbstractDataSourceSupportedBpmFieldConfig {

    private String labelField;

    private String valueField;

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

    public OptionBpmFiledConfig() {
    }

    public OptionBpmFiledConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new OptionValueTypeConfig(configJson));
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
