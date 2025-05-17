package io.github.cloudstars.lowcode.component.form.view;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.form.AbstractFormItemComponentConfig;
import io.github.cloudstars.lowcode.component.form.FormItemComponentClass;

/**
 * 文本展示型组件
 *
 * @author clouds
 */
@FormItemComponentClass(type = "TEXT", readonly = false)
public class TextComponentConfig extends AbstractFormItemComponentConfig {

    public TextComponentConfig() {
        super();
    }

    public TextComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
