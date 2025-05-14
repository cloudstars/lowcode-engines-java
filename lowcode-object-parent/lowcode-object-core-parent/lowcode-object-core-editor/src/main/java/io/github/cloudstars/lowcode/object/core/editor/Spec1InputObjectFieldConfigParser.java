package io.github.cloudstars.lowcode.object.core.editor;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.field.TextObjectFieldConfig;

public class Spec1InputObjectFieldConfigParser extends AbstractSpec1ObjectFieldConfigParser<TextObjectFieldConfig> {

    @Override
    public TextObjectFieldConfig parse(JsonObject configJson) {
        TextObjectFieldConfig config = new TextObjectFieldConfig();
        config.setName(ConfigUtils.getString(configJson, "name"));
        TextValueTypeConfigParser valueTypeConfigParser = (TextValueTypeConfigParser) this.getValueTypeConfigParser("TEXT");
        config.setValueType(valueTypeConfigParser.parse(configJson));

        return config;
    }

}
