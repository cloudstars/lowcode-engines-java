package io.github.cloudstars.lowcode.component.form;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

/**
 * 表单项组件配置
 *
 * @author clouds
 */
public class FormItemConfig<T extends XFormItemComponentConfig> extends AbstractComponentConfig implements XComponentConfig {

    private String name;

    private String label;

    private T component;


    public FormItemConfig() {
    }

    public FormItemConfig(JsonObject configJson) {
        super(configJson);

        this.name = ConfigUtils.getNonNullString(configJson, GlobalAttrNames.ATTR_NAME);
        this.label = ConfigUtils.getNonNullString(configJson, GlobalAttrNames.ATTR_LABEL);
        // this.component =
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
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putAll(configJson, this.component);
        ConfigUtils.put(configJson, GlobalAttrNames.ATTR_NAME, this.name);
        ConfigUtils.put(configJson, GlobalAttrNames.ATTR_LABEL, this.label);

        return configJson;
    }

}
