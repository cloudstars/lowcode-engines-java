package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XIdentifiedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 对象数据下的属性配置
 *
 * @author clouds
 *
 */
public class ObjectPropertyConfig extends AbstractConfig {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性标签
     */
    private String label;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 属性的数据格式
     */
    private XValueTypeConfig valueType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    public ObjectPropertyConfig() {
    }

    public ObjectPropertyConfig(JsonObject configJson) {
        super(configJson);

        this.name = (String) configJson.get(XIdentifiedConfig.ATTR_NAME);
        this.label = (String) configJson.get(XIdentifiedConfig.ATTR_LABEL);
        this.required = (Boolean) configJson.get(XValueTypeConfig.ATTR_REQUIRED);
        this.valueType = ValueTypeConfigFactory.newInstance(configJson);

        Object defaultValue = configJson.get(XValueTypeConfig.ATTR_DEFAULT_VALUE);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XIdentifiedConfig.ATTR_NAME, this.name);
        configJson.put(XIdentifiedConfig.ATTR_LABEL, this.label);
        configJson.putIfNotNull(XValueTypeConfig.ATTR_REQUIRED, this.required);
        configJson.putAll(this.valueType.toJson());

        return configJson;
    }
}
