package io.github.cloudstars.lowcode.form.commons.field;

import io.github.cloudstars.lowcode.commons.data.value.ValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.value.ValueTypeParser;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public class AbstractFieldConfig extends AbstractTypedConfig implements XFieldConfig {

    // 标题属性
    private static final String ATTR_LABEL = "label";

    // 数据格式属性
    private static final String ATTR_VALUE_TYPE = "valueType";

    /**
     * 字段的标题
     */
    private String label;

    /**
     * 字段的数据格式
     */
    private ValueTypeConfig valueType;

    /**
     * 数据格式解析器
     */
    private ValueTypeParser valueTypeParser = new ValueTypeParser();

    protected AbstractFieldConfig() {}

    protected AbstractFieldConfig(JsonObject configJson) {
        super(configJson);

        this.label = (String) configJson.get(ATTR_LABEL);
        Object valueTypeValue = configJson.get(ATTR_VALUE_TYPE);
        this.valueType = this.valueTypeParser.fromJson((JsonObject) valueTypeValue);
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public ValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(ValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_LABEL, this.label);
        configJson.put(ATTR_VALUE_TYPE, this.valueType.toJson());
        return configJson;
    }

}
