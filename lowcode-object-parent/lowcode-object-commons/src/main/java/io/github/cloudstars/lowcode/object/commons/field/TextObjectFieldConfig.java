package io.github.cloudstars.lowcode.object.commons.field;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.TextValueTypeConfig;

/**
 * 文本字段配置
 *
 * @author clouds
 */
@ObjectFieldConfigClass(name = "TEXT")
public class TextObjectFieldConfig extends AbstractObjectFieldConfig {

    /* 占位提示配置项名称 */
    private static final String ATTR_PLACEHOLDER = "placeholder";

    private String placeholder;

    public TextObjectFieldConfig() {
    }

    public TextObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setValueType(new TextValueTypeConfig(configJson));
        this.placeholder = ConfigUtils.getString(configJson, ATTR_PLACEHOLDER);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, ATTR_PLACEHOLDER, this.placeholder);

        return configJson;
    }

}
