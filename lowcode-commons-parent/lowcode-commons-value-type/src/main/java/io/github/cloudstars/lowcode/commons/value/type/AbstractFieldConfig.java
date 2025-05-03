package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public abstract class AbstractFieldConfig extends AbstractConfig {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段标签
     */
    private String label;

    public AbstractFieldConfig() {
    }

    public AbstractFieldConfig(JsonObject configJson) {
        super(configJson);

        this.name = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_NAME);
        this.label = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_LABEL);
    }

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

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, GlobalAttrNames.ATTR_NAME, this.name);
        ConfigUtils.put(configJson, GlobalAttrNames.ATTR_LABEL, this.label);

        return configJson;
    }
}
