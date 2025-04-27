package io.github.cloudstars.lowcode.component.commons;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的组件配置，所有组件的配置都继承它
 *
 * @author clouds
 */
public abstract class AbstractComponentConfig extends AbstractTypedConfig implements XComponentConfig {

    // 组件ID配置名称
    private static final String ATTR_ID = "id";

    // 显示条件配置名称
    private static final String ATTR_VISIBLE_ON = "visibleOn";

    /**
     * 组件的ID
     */
    private String id;

    /**
     * 显示条件
     */
    private String visibleOn;

    public AbstractComponentConfig() {
    }

    public AbstractComponentConfig(JsonObject configJson) {
        super(configJson);

        this.id = (String) configJson.get(ATTR_ID);
        this.visibleOn = (String) configJson.get(ATTR_VISIBLE_ON);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, ATTR_ID, this.id);
        ConfigUtils.putIfNotNull(configJson, ATTR_VISIBLE_ON, this.visibleOn);

        return configJson;
    }

}
