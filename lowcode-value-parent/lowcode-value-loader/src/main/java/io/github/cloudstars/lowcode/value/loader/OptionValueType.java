package io.github.cloudstars.lowcode.value.loader;

import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;
import io.github.cloudstars.lowcode.value.type.Option;
import io.github.cloudstars.lowcode.value.type.OptionValueTypeConfig;

import java.util.Map;

/**
 * 选项数据格式
 *
 * @author clouds
 */
@ValueTypeClass(name = "OPTION", valueTypeConfigClass = OptionValueTypeConfig.class)
public class OptionValueType extends AbstractValueType<OptionValueTypeConfig, Option> {

    // 标签配置名称
    private static final String DEFAULT_LABEL_FIELD = "labelField";

    // 值配置名称
    private static final String DEFAULT_VALUE_FIELD = "valueField";

    private String labelField;

    private String valueField;

    public OptionValueType(OptionValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);

        this.labelField = valueTypeConfig.getLabelField();
        this.valueField = valueTypeConfig.getValueField();
    }

    @Override
    public Option parseDefaultValue() throws InvalidDataException {
        return null;
    }

    @Override
    public Option mergeDefaultValue(Object rawValue) throws InvalidDataException {
        if (rawValue instanceof Map) {
            Map defaultValueConfigMap = (Map) rawValue;
            String labelField = ObjectUtils.getOrDefault(valueTypeConfig.getLabelField(), DEFAULT_LABEL_FIELD);
            String valueField = ObjectUtils.getOrDefault(valueTypeConfig.getValueField(), DEFAULT_VALUE_FIELD);
            Option option = new Option();
            option.setLabel((String) defaultValueConfigMap.get(labelField));
            option.setValue((String) defaultValueConfigMap.get(valueField));
            return option;
        }

        return (Option) rawValue;
    }

    @Override
    public Option parseValue(Object rawValue) {
        if (rawValue instanceof Map) {
            Map<String, Object> valueMap = (Map<String, Object>) rawValue;
            Option option = new Option();
            option.setLabel((String) valueMap.get(this.labelField));
            option.setValue((String) valueMap.get(this.valueField));
            return option;
        }

        return (Option) rawValue;
    }

    @Override
    public void validate(Option value) throws InvalidDataException {

    }

}