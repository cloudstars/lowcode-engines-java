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

    /**
     * 文本组件的内容
     */
    private String content;

    public TextComponentConfig() {
        super();
    }

    public TextComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
