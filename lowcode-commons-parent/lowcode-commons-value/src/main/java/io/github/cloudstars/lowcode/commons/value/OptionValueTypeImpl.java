package io.github.cloudstars.lowcode.commons.value;

import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;
import io.github.cloudstars.lowcode.commons.value.type.OptionValue;
import io.github.cloudstars.lowcode.commons.value.type.OptionValueTypeConfig;

import java.util.Map;

/**
 * 选项数据格式
 *
 * @author clouds
 */
@ValueTypeClass(name = "OPTION", valueTypeConfigClass = OptionValueTypeConfig.class)
public class OptionValueTypeImpl extends AbstractValueTypeImpl<OptionValueTypeConfig, OptionValue> {

    // 标签配置名称
    private static final String DEFAULT_LABEL_FIELD = "labelField";

    // 值配置名称
    private static final String DEFAULT_VALUE_FIELD = "valueField";

    private String labelField;

    private String valueField;

    public OptionValueTypeImpl(OptionValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);

        this.labelField = valueTypeConfig.getLabelField();
        this.valueField = valueTypeConfig.getValueField();
    }

    @Override
    public OptionValue parseDefaultValue() throws InvalidDataException {
        return null;
    }

    @Override
    public OptionValue mergeDefaultValue(Object rawValue) throws InvalidDataException {
        if (rawValue instanceof Map) {
            Map defaultValueConfigMap = (Map) rawValue;
            String labelField = ObjectUtils.getOrDefault(valueTypeConfig.getLabelField(), DEFAULT_LABEL_FIELD);
            String valueField = ObjectUtils.getOrDefault(valueTypeConfig.getValueField(), DEFAULT_VALUE_FIELD);
            OptionValue optionValue = new OptionValue();
            optionValue.setLabel((String) defaultValueConfigMap.get(labelField));
            optionValue.setValue((String) defaultValueConfigMap.get(valueField));
            return optionValue;
        }

        return (OptionValue) rawValue;
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