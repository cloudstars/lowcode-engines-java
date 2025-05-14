package io.github.cloudstars.lowcode.object.core.editor;

import io.github.cloudstars.lowcode.object.commons.field.AbstractObjectFieldConfig;

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

}
