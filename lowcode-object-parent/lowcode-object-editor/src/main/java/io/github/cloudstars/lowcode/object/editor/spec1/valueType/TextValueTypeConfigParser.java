package io.github.cloudstars.lowcode.object.editor.spec1.valueType;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.TextValueTypeConfig;

public class TextValueTypeConfigParser implements Spec1ValueTypeConfigParser<TextValueTypeConfig> {

    @Override
    public TextValueTypeConfig parse(JsonObject configJson) {
        TextValueTypeConfig config = new TextValueTypeConfig();
        config.setRequired(ConfigUtils.getBoolean(configJson, "required"));
        config.setMaxLength(ConfigUtils.getInteger(configJson, "maxLength"));

        return config;
    }
}
