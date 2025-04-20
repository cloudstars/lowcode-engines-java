package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.data.valuetype.config.OptionValue;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.OptionValueTypeConfig;

import java.util.Map;

public class OptionValueTypeImpl extends AbstractValueTypeImpl<OptionValueTypeConfig, OptionValue> {

    private String labelField;

    private String valueField;

    public OptionValueTypeImpl(OptionValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);

        this.labelField = valueTypeConfig.getLabelField();
        this.valueField = valueTypeConfig.getValueField();
    }

    @Override
    public OptionValue parseValue(Object rawValue) {
        if (rawValue instanceof Map) {
            Map<String, Object> valueMap = (Map<String, Object>) rawValue;
            OptionValue optionValue = new OptionValue();
            optionValue.setLabel((String) valueMap.get(this.labelField));
            optionValue.setValue((String) valueMap.get(this.valueField));
            return optionValue;
        }

        return (OptionValue) rawValue;
    }

    @Override
    public void validate(OptionValue value) throws InvalidDataException {

    }

}