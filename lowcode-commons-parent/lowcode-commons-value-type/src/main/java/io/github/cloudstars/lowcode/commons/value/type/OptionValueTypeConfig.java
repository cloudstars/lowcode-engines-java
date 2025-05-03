package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Arrays;

/**
 * 选项数据格式
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "OPTION", valueClass = OptionValue.class)
public class OptionValueTypeConfig extends AbstractObjectValueTypeConfig {

    /**
     * 标签配置名称
     */
    private String labelField;

    /**
     * 值配置名称
     */
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

    public OptionValueTypeConfig() {
    }

    public OptionValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.labelField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_LABLE_FIELD);
        this.valueField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_VALUE_FIELD);

        ObjectPropertyConfig labelPropertyConfig = new ObjectPropertyConfig();
        {
            labelPropertyConfig.setName(this.labelField);
            labelPropertyConfig.setLabel("标签");
            TextValueTypeConfig labelValueTypeConfig = new TextValueTypeConfig();
            labelPropertyConfig.setValueType(labelValueTypeConfig);
        }

        ObjectPropertyConfig valuePropertyConfig = new ObjectPropertyConfig();
        {
            valuePropertyConfig.setName(this.valueField);
            valuePropertyConfig.setLabel("值");
            TextValueTypeConfig valueValueTypeConfig = new TextValueTypeConfig();
            valuePropertyConfig.setValueType(valueValueTypeConfig);
        }

        this.properties = Arrays.asList(labelPropertyConfig, valuePropertyConfig);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, GlobalAttrNames.ATTR_LABLE_FIELD, this.labelField);
        ConfigUtils.putIfNotNull(configJson, GlobalAttrNames.ATTR_VALUE_FIELD, this.valueField);

        return configJson;
    }

}
