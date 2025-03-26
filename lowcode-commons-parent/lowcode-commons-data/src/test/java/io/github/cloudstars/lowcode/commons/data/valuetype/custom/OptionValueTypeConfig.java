package io.github.cloudstars.lowcode.commons.data.valuetype.custom;

import io.github.cloudstars.lowcode.commons.data.DataTypeEnum;
import io.github.cloudstars.lowcode.commons.data.valuetype.*;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.lang.value.InvalidValueFormatException;

import java.util.Arrays;
import java.util.Map;

/**
 * 选项数据格式
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "OPTION")
public class OptionValueTypeConfig extends AbstractObjectValueTypeConfig<OptionValue> {

    private String labelField = "label";

    private String valueField = "value";

    public OptionValueTypeConfig(JsonObject configJson) {
        super(configJson);

        Object propertiesConfig = configJson.get("properties");
        if (propertiesConfig != null) {
            JsonArray propertiesConfigJson = (JsonArray) propertiesConfig;
            // 解析label、value属性
            for (Object propertyConfig : propertiesConfigJson) {
                JsonObject propertyConfigJson = (JsonObject) propertyConfig;
                String propertyName = (String) propertyConfigJson.get("name");
                ValueTypeConfig propertyValueType = ValueTypeFactory.newInstance(propertyConfigJson);
                ObjectValueProperty objectValueProperty = new ObjectValueProperty(propertyName, propertyValueType);
                this.properties.add(objectValueProperty);
            }

            if (this.properties.size() < 2) {
                throw new RuntimeException("选项数据格式必须定义label与value两个属性");
            }

            ObjectValueProperty firstValueProperty = this.properties.get(0);
            if (!"label".equals(firstValueProperty.getName())) {
                this.properties = Arrays.asList(this.properties.get(1), firstValueProperty);
            }
        }


        // 默认值需要在所有属性解析完之后再解析
        this.defaultValue = this.parseDefaultValue(configJson);
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.OBJECT;
    }

    @Override
    protected OptionValue parseDefaultValue(Object defaultValueConfig) {
        OptionValue defaultValue = null;
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof Map) {
                Map<String, Object> valueMap = (Map<String, Object>) defaultValueConfig;
                defaultValue = new OptionValue();
                defaultValue.setLabel((String) valueMap.get(this.labelField));
                defaultValue.setValue((String) valueMap.get(this.valueField));
            } else {
                throw new InvalidValueFormatException("选项数据格式不正确，请检查您的数据：" + JsonUtils.toJsonString(defaultValueConfig));
            }
        } else {
            Object labelDefaultValue = this.properties.get(0).getValueType().getDefaultValue();
            Object valueDefaultValue = this.properties.get(1).getValueType().getDefaultValue();
            if (labelDefaultValue != null || valueDefaultValue != null) {
                defaultValue = new OptionValue();
                defaultValue.setLabel((String) labelDefaultValue);
                defaultValue.setValue((String) valueDefaultValue);
            }
        }

        return defaultValue;
    }

    @Override
    public Object parseNonNullValue(Object nonNullValue) {
        return null;
    }

    public String getLabelField() {
        return labelField;
    }

    public void setLabelField(String labelField) {
        this.labelField = labelField;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

}
