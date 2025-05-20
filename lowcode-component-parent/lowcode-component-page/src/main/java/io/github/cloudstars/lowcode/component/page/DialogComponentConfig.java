package io.github.cloudstars.lowcode.component.page;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

/**
 * 对话框组件配置
 *
 * @author clouds 
 */
public class DialogComponentConfig extends AbstractComponentConfig implements XComponentConfig {

    public DialogComponentConfig() {
    }

    public DialogComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
