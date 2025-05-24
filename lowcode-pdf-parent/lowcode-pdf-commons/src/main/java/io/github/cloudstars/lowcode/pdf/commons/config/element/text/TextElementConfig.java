package io.github.cloudstars.lowcode.pdf.commons.config.element.text;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.TextValueTypeConfig;
import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;

/**
 * PDF文本元素
 *
 * @author clouds
 */
public class TextElementConfig extends AbstractElementConfig<TextValueTypeConfig> {

    public TextElementConfig() {
    }

    public TextElementConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public String getType() {
        return "TEXT";
    }

    @Override
    public JsonObject<String, Object> toJson() {
        return super.toJson();
    }
    
}
