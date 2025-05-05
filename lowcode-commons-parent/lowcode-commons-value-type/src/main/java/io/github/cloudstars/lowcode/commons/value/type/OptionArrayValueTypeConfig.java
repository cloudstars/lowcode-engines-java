package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 选项数组数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "OPTION-ARRAY", valueClass = List.class)
public class OptionArrayValueTypeConfig extends AbstractArrayValueTypeConfig {

    private String labelField;

    private String valueField;

    public OptionArrayValueTypeConfig() {
        OptionValueTypeConfig optionValueTypeConfig = new OptionValueTypeConfig();
        this.itemsValueType = optionValueTypeConfig;
    }

    public OptionArrayValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.labelField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_LABEL_FIELD);
        this.valueField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_VALUE_FIELD);

        OptionValueTypeConfig optionValueTypeConfig = new OptionValueTypeConfig();
        optionValueTypeConfig.setLabelField(labelField);
        optionValueTypeConfig.setValueField(valueField);
        this.itemsValueType = optionValueTypeConfig;
    }

    public String getLabelField() {
        return labelField;
    }

    public void setLabelField(String labelField) {
        this.labelField = labelField;
        OptionValueTypeConfig itemValueType = (OptionValueTypeConfig) this.itemsValueType;
        itemValueType.setLabelField(labelField);
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
        OptionValueTypeConfig itemValueType = (OptionValueTypeConfig) this.itemsValueType;
        itemValueType.setLabelField(valueField);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, GlobalAttrNames.ATTR_LABEL_FIELD, this.labelField);
        ConfigUtils.putIfNotNull(configJson, GlobalAttrNames.ATTR_VALUE_FIELD, this.valueField);

        return configJson;
    }

}
