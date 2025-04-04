package io.github.cloudstars.lowcode.object.view.commons.config.form;

import io.github.cloudstars.lowcode.commons.lang.config.ConfigTypeClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.config.ObjectConfig;
import io.github.cloudstars.lowcode.object.view.commons.config.AbstractViewConfig;

/**
 * 表单视图配置
 *
 * @author clouds
 */
@ConfigTypeClass(code = "FormView", name = "表单视图")
public class FormViewConfig extends AbstractViewConfig {

    public FormViewConfig() {
    }

    public FormViewConfig(JsonObject configJson) {
        super(configJson);
    }

    public FormViewConfig(ObjectConfig objectConfig) {

    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
