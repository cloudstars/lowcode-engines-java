package io.github.cloudstars.lowcode.component.form;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;

/**
 * 抽象的表单项组件配置
 *
 * @author clouds
 */
public abstract class AbstractFormItemComponentConfig<T extends XFormItemComponentConfig> extends AbstractComponentConfig implements XFormItemComponentConfig {


    public AbstractFormItemComponentConfig() {
    }

    public AbstractFormItemComponentConfig(JsonObject configJson) {
        super(configJson);

    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
