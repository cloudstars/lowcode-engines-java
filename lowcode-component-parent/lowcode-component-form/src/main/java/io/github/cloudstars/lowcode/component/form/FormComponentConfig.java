package io.github.cloudstars.lowcode.component.form;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;

import java.util.List;

/**
 * 表单组件配置
 *
 * @author clouds
 */
public class FormComponentConfig extends AbstractComponentConfig {

    private static final String ATTR_ITEMS = "items";

    /**
     * 表单项列表
     */
    private List<FormItemConfig> items;

    public FormComponentConfig() {
    }

    public FormComponentConfig(JsonObject configJson) {
        super(configJson);

        this.items = ConfigUtils.getList(configJson, ATTR_ITEMS, (v) -> null);
    }

    public List<FormItemConfig> getItems() {
        return items;
    }

    public void setItems(List<FormItemConfig> items) {
        this.items = items;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putJsonArray(configJson, ATTR_ITEMS, this.items);

        return configJson;
    }

}
