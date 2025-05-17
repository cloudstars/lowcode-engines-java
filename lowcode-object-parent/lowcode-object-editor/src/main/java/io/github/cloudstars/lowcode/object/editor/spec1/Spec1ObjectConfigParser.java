package io.github.cloudstars.lowcode.object.editor.spec1;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.FormBasedObjectConfig;
import io.github.cloudstars.lowcode.object.commons.field.XObjectFieldConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spec1ObjectConfigParser implements XConfigParser<FormBasedObjectConfig> {

    private final Map<String, Spec1ObjectFieldConfigParser> fieldConfigParserMap = new HashMap<>();

    public Spec1ObjectConfigParser() {
        this.fieldConfigParserMap.put("Input", new Spec1InputObjectFieldConfigParser());
    }

    @Override
    public FormBasedObjectConfig parse(JsonObject configJson) {
        FormBasedObjectConfig config = new FormBasedObjectConfig();
        config.setKey(ConfigUtils.getNonNullString(configJson, "objectKey"));
        config.setCode(ConfigUtils.getNonNullString(configJson, "objectCode"));
        config.setName(ConfigUtils.getNonNullString(configJson, "objectName"));
        config.setDescription(ConfigUtils.getString(configJson, "description"));
        config.setTableName(ConfigUtils.getNonNullString(configJson, "tableName"));
        config.setPrimaryFieldKey(ConfigUtils.getNonNullString(configJson, "primaryFieldKey"));
        config.setEnableEnterprise(ConfigUtils.getBoolean(configJson, "enableEnterprise"));
        JsonArray fieldsConfigJson = ConfigUtils.getJsonArray(configJson, "body");
        config.setFields(this.parseFields(fieldsConfigJson));

        return config;
    }

    private List<XObjectFieldConfig> parseFields(JsonArray fieldsConfigJson) {
        List<XObjectFieldConfig> fieldConfigs = new ArrayList<>();
        for (Object object : fieldsConfigJson) {
            JsonObject fieldConfigJson = (JsonObject) object;
            String type = ConfigUtils.getString(fieldConfigJson, "type");
            Spec1ObjectFieldConfigParser<XObjectFieldConfig> fieldConfigParser = this.fieldConfigParserMap.get(type);
            if (fieldConfigParser != null) {
                fieldConfigs.add(fieldConfigParser.parse(fieldConfigJson));
            } else {
                // fieldConfigs.add(new OtherObjectFieldConfig(fieldConfigJson));
            }
        }

        return fieldConfigs;
    }

}
