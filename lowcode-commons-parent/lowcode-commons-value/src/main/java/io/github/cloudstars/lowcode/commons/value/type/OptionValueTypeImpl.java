package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.value.type.config.OptionValueTypeConfig;

import java.util.Map;

public class OptionValueTypeImpl extends AbstractValueTypeImpl<OptionValueTypeConfig, OptionObject> {

    private String labelField;

    private String valueField;

    public OptionValueTypeImpl(OptionValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);

        this.labelField = valueTypeConfig.getLabelField();
        this.valueField = valueTypeConfig.getValueField();
    }

    @Override
    public OptionObject parseValue(Object rawValue) {
        if (rawValue instanceof Map) {
            Map<String, Object> valueMap = (Map<String, Object>) rawValue;
            OptionObject optionObject = new OptionObject();
            optionObject.setLabel((String) valueMap.get(this.labelField));
            optionObject.setValue((String) valueMap.get(this.valueField));
            return optionObject;
        }

        return (OptionObject) rawValue;
    }

    @Override
    public void validate(OptionObject value) throws InvalidDataException {

    }

}