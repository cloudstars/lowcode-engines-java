package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * 选项数据格式
 *
 * @author clouds
 */
@DataTypeConfigClass(name = "OPTION")
public class OptionDataTypeConfig extends AbstractObjectDataTypeConfig<OptionObject> {

    private String labelField = "label";

    private String valueField = "value";

    public OptionDataTypeConfig(JsonObject configJson) {
        super(configJson);

        Object propertiesConfig = configJson.get("properties");
        if (propertiesConfig != null) {
            JsonArray propertiesConfigJson = (JsonArray) propertiesConfig;
            // 解析label、value属性
            for (Object propertyConfig : propertiesConfigJson) {
                JsonObject propertyConfigJson = (JsonObject) propertyConfig;
                String propertyName = (String) propertyConfigJson.get("name");
                DataTypeConfig propertyValueType = DataTypeConfigFactory.newInstance(propertyConfigJson);
                ObjectProperty objectProperty = new ObjectProperty(propertyName, propertyValueType);
                this.properties.add(objectProperty);
            }

            if (this.properties.size() < 2) {
                throw new RuntimeException("选项数据格式必须定义label与value两个属性");
            }

            ObjectProperty firstValueProperty = this.properties.get(0);
            if (!"label".equals(firstValueProperty.getName())) {
                this.properties = Arrays.asList(this.properties.get(1), firstValueProperty);
            }
        }


        // 默认值需要在所有属性解析完之后再解析
        this.defaultValue = this.parseDefaultValue(configJson);
    }

    @Override
    protected OptionObject parseDefaultValue(Object defaultValueConfig) {
        OptionObject defaultValue = null;
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof Map) {
                Map<String, Object> valueMap = (Map<String, Object>) defaultValueConfig;
                defaultValue = new OptionObject();
                defaultValue.setLabel((String) valueMap.get(this.labelField));
                defaultValue.setValue((String) valueMap.get(this.valueField));
            } else {
                throw new InvalidDataException("选项数据格式不正确，请检查您的数据：" + JsonUtils.toJsonString(defaultValueConfig));
            }
        } else {
            Object labelDefaultValue = this.properties.get(0).getDataType().getDefaultValue();
            Object valueDefaultValue = this.properties.get(1).getDataType().getDefaultValue();
            if (labelDefaultValue != null || valueDefaultValue != null) {
                defaultValue = new OptionObject();
                defaultValue.setLabel((String) labelDefaultValue);
                defaultValue.setValue((String) valueDefaultValue);
            }
        }

        return defaultValue;
    }

    @Override
    public OptionObject parseNonNullValue(Object nonNullValue) {
        if (nonNullValue instanceof Map) {
            Map<String, Object> valueMap = (Map<String, Object>) nonNullValue;
            OptionObject optionObject = new OptionObject();
            optionObject.setLabel((String) valueMap.get(this.labelField));
            optionObject.setValue((String) valueMap.get(this.valueField));
            return optionObject;
        }

        return (OptionObject) nonNullValue;
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

    @Override
    public void validate(OptionObject nonNullValue) throws InvalidDataException {

    }

}
