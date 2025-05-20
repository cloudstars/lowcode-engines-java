package io.github.cloudstars.lowcode.object.form.editor;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.AbstractObjectViewConfig;

/**
 * 模型表单类视图配置
 *
 * @author clouds
 */
public class AbstractObjectFormViewConfig extends AbstractObjectViewConfig {
    
    public AbstractObjectFormViewConfig() {
    }

    public AbstractObjectFormViewConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
