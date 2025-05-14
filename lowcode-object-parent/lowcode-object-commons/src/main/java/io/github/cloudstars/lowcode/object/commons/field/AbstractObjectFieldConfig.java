package io.github.cloudstars.lowcode.object.commons.field;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;
import io.github.cloudstars.lowcode.dynamic.value.DynamicValueConfigFactory;
import io.github.cloudstars.lowcode.dynamic.value.XDynamicValueConfig;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public abstract class AbstractObjectFieldConfig extends AbstractResourceConfig implements XObjectFieldConfig {

    // private static final String ATTR_OWNER = "owner";

    private static final String ATTR_COLUMN_NAME = "columnName";

    private static final String ATTR_IS_AUTO_GEN = "isAutoGen";



    /**
     * 字段所属的模型名称
     */
    // private String owner;

    /**
     * 字段的名称
     */
    private String name;

    /**
     * 字段的列名
     */
    private String columnName;

    /**
     * 是否自动生成的字段
     */
    private Boolean autoGen;

    /**
     * 数据格式配置
     */
    private XValueTypeConfig valueType;

    /**
     * 动态值配置
     */
    private XDynamicValueConfig dynamicValue;

    protected AbstractObjectFieldConfig() {
        this.setType(this.getClass().getAnnotation(ObjectFieldConfigClass.class).name());
    }

    protected AbstractObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(ObjectFieldConfigClass.class).name());

        //this.owner = ConfigUtils.getString(configJson, ATTR_OWNER);
        this.name = ConfigUtils.getString(configJson, ATTR_NAME);
        ConfigUtils.consumeIfPresent(configJson, ATTR_COLUMN_NAME, (v) -> {
            this.columnName = (String) v;
        });
        ConfigUtils.consumeIfPresent(configJson, ATTR_IS_AUTO_GEN, (v) -> {
            this.autoGen = (Boolean) v;
        });
        JsonObject dynamicValueConfigJson = (JsonObject) configJson.get(XDynamicValueConfig.ATTR);
        if (dynamicValueConfigJson != null) {
            this.dynamicValue = DynamicValueConfigFactory.newInstance(dynamicValueConfigJson);
        }
    }

    /*@Override
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }*/

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Boolean getAutoGen() {
        return autoGen;
    }

    public void setAutoGen(Boolean autoGen) {
        this.autoGen = autoGen;
    }

    @Override
    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    @Override
    public XDynamicValueConfig getDynamicValue() {
        return dynamicValue;
    }

    public void setDynamicValue(XDynamicValueConfig dynamicValue) {
        this.dynamicValue = dynamicValue;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        //ConfigUtils.put(configJson, ATTR_OWNER, this.owner);
        ConfigUtils.put(configJson, ATTR_NAME, this.name);
        ConfigUtils.putIfNotNull(configJson, ATTR_COLUMN_NAME, this.columnName);
        ConfigUtils.putIfNotNull(configJson, ATTR_IS_AUTO_GEN, this.autoGen);

        // 将数据格式的属性添加到当前字段上
        if (this.valueType != null) {
            JsonObject<String, Object> valueTypeConfigJson = this.valueType.toJson();
            valueTypeConfigJson.forEach((k, v) -> {
                if (!XTypedConfig.ATTR.equalsIgnoreCase(k)) { // 避免数据格式的类型覆盖字段类型
                    configJson.put(k, v);
                }
            });
        }

        ConfigUtils.putJsonIfNotNull(configJson, XDynamicValueConfig.ATTR, this.dynamicValue);

        return configJson;
    }

}
