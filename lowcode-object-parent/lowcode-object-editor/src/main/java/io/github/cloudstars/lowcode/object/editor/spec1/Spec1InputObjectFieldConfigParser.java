package io.github.cloudstars.lowcode.object.editor.spec1;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.field.TextObjectFieldConfig;
import io.github.cloudstars.lowcode.object.editor.spec1.valueType.TextValueTypeConfigParser;

public class Spec1InputObjectFieldConfigParser extends AbstractSpec1ObjectFieldConfigParser<TextObjectFieldConfig> {

    @Override
    public TextObjectFieldConfig parse(JsonObject configJson) {
        TextObjectFieldConfig config = new TextObjectFieldConfig();
        super.setCommons(config, configJson);
        TextValueTypeConfigParser valueTypeConfigParser = (TextValueTypeConfigParser) this.getValueTypeConfigParser("TEXT");
        config.setValueType(valueTypeConfigParser.parse(configJson));
        config.setPlaceholder(ConfigUtils.getString(configJson, "placeholder"));

        return config;
    }

}
