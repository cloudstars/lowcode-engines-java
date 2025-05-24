package io.github.cloudstars.lowcode.object.editor.spec1;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.commons.field.AbstractObjectFieldConfig;
import io.github.cloudstars.lowcode.object.editor.spec1.valueType.Spec1ValueTypeConfigParser;
import io.github.cloudstars.lowcode.object.editor.spec1.valueType.TextValueTypeConfigParser;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSpec1ObjectFieldConfigParser<T extends AbstractObjectFieldConfig> implements Spec1ObjectFieldConfigParser<T> {

    private final Map<String, Spec1ValueTypeConfigParser> valueTypeConfigParserMap = new HashMap<>();

    public AbstractSpec1ObjectFieldConfigParser() {
        this.valueTypeConfigParserMap.put("TEXT", new TextValueTypeConfigParser());
    }

    protected Spec1ValueTypeConfigParser getValueTypeConfigParser(String type) {
        return this.valueTypeConfigParserMap.get(type);
    }

    protected void setCommons(AbstractObjectFieldConfig config, JsonObject configJson) {
        config.setKey(ConfigUtils.getRequiredString(configJson,"fieldKey"));
        config.setCode(ConfigUtils.getRequiredString(configJson, "name"));
        config.setTitle(ConfigUtils.getRequiredString(configJson, "title"));
        config.setDescription(ConfigUtils.getString(configJson, "description"));
        //config.setTitle(ConfigUtils.getNonNullString(configJson, "title"));
        config.setColumnName(ConfigUtils.getString(configJson, "columnName"));
        config.setDefaultWidth(ConfigUtils.getInteger(configJson, "defaultWidth"));
        config.setSystemField(ConfigUtils.getBoolean(configJson, "systemField"));
        //config.setDeamonField(ConfigUtils.getBoolean(configJson, "daemonField"));
    }

}
