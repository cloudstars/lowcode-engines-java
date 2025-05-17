package io.github.cloudstars.lowcode.component.form.input;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.form.AbstractFormItemComponentConfig;
import io.github.cloudstars.lowcode.component.form.FormItemComponentClass;

/**
 * 文本输入型组件的配置
 *
 * @author clouds
 */
@FormItemComponentClass(type = "TextInput")
public class TextInputComponentConfig extends AbstractFormItemComponentConfig {

    public TextInputComponentConfig() {
    }

    public TextInputComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
