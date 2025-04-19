package io.github.cloudstars.lowcode.form.commons.field;

import io.github.cloudstars.lowcode.commons.data.defaultvalue.AbstractDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.ValueTypeConfigParser;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public class AbstractFieldConfig extends AbstractTypedConfig implements XFieldConfig {

    /**
     * 字段的标题
     */
    private String title;

    /**
     * 字段的数据格式
     */
    private XValueTypeConfig valueType;

    /**
     * 默认值（后端的缺省值）
     */
    private AbstractDefaultValueConfig defaultValue;

    /**
     * 数据格式解析器
     */
    private ValueTypeConfigParser valueTypeConfigParser = new ValueTypeConfigParser();

    protected AbstractFieldConfig() {}

    protected AbstractFieldConfig(JsonObject configJson) {
        super(configJson);

        this.title = (String) configJson.get(ATTR_TITLE);
        Object valueTypeValue = configJson.get(XValueTypeConfig.ATTR);
        this.valueType = this.valueTypeConfigParser.fromJson((JsonObject) valueTypeValue);
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    public AbstractDefaultValueConfig getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(AbstractDefaultValueConfig defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_TITLE, this.title);
        configJson.put(XValueTypeConfig.ATTR, this.valueType.toJson());

        return configJson;
    }

}
