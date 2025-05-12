package io.github.cloudstars.lowcode.pdf.parser;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.pdf.commons.config.element.text.TextElementConfig;

public class TextElementConfigParser extends AbstractElementConfigParser<TextElementConfig> {

    @Override
    public TextElementConfig parse(JsonObject configJson) {
        TextElementConfig config = new TextElementConfig();
        config.setType("TEXT");
        super.parseCommons(config, configJson);

        return config;
    }

}
