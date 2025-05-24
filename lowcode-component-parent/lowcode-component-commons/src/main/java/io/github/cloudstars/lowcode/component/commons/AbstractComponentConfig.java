package io.github.cloudstars.lowcode.component.commons;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.XResourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.style.StyleConfig;

/**
 * 抽象的组件配置，所有组件的配置都继承它
 *
 * @author clouds
 */
public abstract class AbstractComponentConfig extends AbstractTypedConfig implements XComponentConfig {

    // 组件ID配置名称
    // private static final String ATTR_ID = "id";

    // 显示条件配置名称
    private static final String ATTR_VISIBLE_ON = "visibleOn";

    /**
     * 组件的ID
     */
    private String key;

    /**
     * 显示条件
     */
    private String visibleOn;

    /**
     * 样式
     */
    private StyleConfig style;

    public AbstractComponentConfig() {
    }

    public AbstractComponentConfig(JsonObject configJson) {
        super(configJson);

        this.key = ConfigUtils.getString(configJson, XResourceConfig.ATTR_KEY);
        this.visibleOn = (String) configJson.get(ATTR_VISIBLE_ON);
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public StyleConfig getStyle() {
        return this.style;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, XResourceConfig.ATTR_KEY, this.key);
        ConfigUtils.put(configJson, ATTR_VISIBLE_ON, this.visibleOn);

        return configJson;
    }

}
