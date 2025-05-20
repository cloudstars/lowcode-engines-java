package io.github.cloudstars.lowcode.component.page;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

/**
 * 页面组件配置
 *
 * @author clouds
 */
public class PageComponentConfig extends AbstractComponentConfig implements XComponentConfig {

    public PageComponentConfig() {
    }

    public PageComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
