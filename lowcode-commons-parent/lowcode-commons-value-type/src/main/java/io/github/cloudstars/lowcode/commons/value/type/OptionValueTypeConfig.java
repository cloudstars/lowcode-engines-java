package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 选项数据格式
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "OPTION")
public class OptionValueTypeConfig extends AbstractObjectValueTypeConfig<OptionValue> {

    // 标签配置名称
    private static final String ATTR_LABEL = "labelField";

    // 值配置名称
    private static final String ATTR_VALUE = "valueField";

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

        this.labelField = (String) configJson.get(ATTR_LABEL);
        this.valueField = (String) configJson.get(ATTR_VALUE);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.putIfNotNull(ATTR_LABEL, this.labelField);
        configJson.putIfNotNull(ATTR_VALUE, this.valueField);

        return configJson;
    }

}
