package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 对象数据下的属性配置
 *
 * @author clouds
 */
public class MapPropertyConfig extends AbstractConfig {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性标签
     */
    private String label;

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

    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    public MapPropertyConfig() {
    }

    public MapPropertyConfig(JsonObject configJson) {
        super(configJson);

        this.name = (String) configJson.get(GlobalAttrNames.ATTR_NAME);
        this.label = (String) configJson.get(GlobalAttrNames.ATTR_LABEL);
        this.valueType = ValueTypeConfigFactory.newInstance(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putRequired(configJson, GlobalAttrNames.ATTR_NAME, this.name);
        ConfigUtils.putRequired(configJson, GlobalAttrNames.ATTR_LABEL, this.label);
        ConfigUtils.putAll(configJson, this.valueType);

        return configJson;
    }
}
