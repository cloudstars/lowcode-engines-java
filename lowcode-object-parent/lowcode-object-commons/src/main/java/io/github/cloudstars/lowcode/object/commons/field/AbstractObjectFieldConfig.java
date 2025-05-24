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

    // 字段列名称
    private static final String ATTR_COLUMN_NAME = "columnName";
    // 是否主键字段
    private static final String ATTR_PRIMARY_FIELD = "primaryField";
    // 是否名称字段
    private static final String ATTR_NAME_FIELD = "nameField";
    // 是否自动生成字段
    private static final String ATTR_AUTO_GEN = "autoGen";
    // 字段默认宽度
    private static final String ATTR_DEFAULT_WIDTH = "defaultWidth";
    // 是否系统字段
    private static final String ATTR_SYSTEM_FIELD = "systemField";
    // 是否后台计算字段
    // private static final String ATTR_DEAMON_FIELD = "deamonField";


    /**
     * 字段标题
     */
    //private String title;

    /**
     * 字段的列名
     */
    private String columnName;

    /**
     * 是否主键字段
     */
    private Boolean primaryField;

    /**
     * 是否自动生成的字段
     */
    private Boolean autoGen;

    /**
     * 是否名称字段
     */
    private Boolean nameField;

    /**
     * 是否系统字段
     */
    private Boolean systemField;

    /**
     * 是否后台计算字段
     */
    //private Boolean deamonField;

    /**
     * 默认宽度
     */
    private Integer defaultWidth;

    /**
     * 数据格式配置
     */
    private XValueTypeConfig valueType;

    /**
     * 动态值配置
     */
    private XDynamicValueConfig dynamicValue;

    protected AbstractObjectFieldConfig() {
    }

    protected AbstractObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        //this.title = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_LABEL);
        this.columnName = ConfigUtils.getString(configJson, ATTR_COLUMN_NAME);
        this.primaryField = ConfigUtils.getBoolean(configJson, ATTR_PRIMARY_FIELD);
        this.autoGen = ConfigUtils.getBoolean(configJson, ATTR_AUTO_GEN);
        this.nameField = ConfigUtils.getBoolean(configJson, ATTR_NAME_FIELD);
        this.systemField = ConfigUtils.getBoolean(configJson, ATTR_SYSTEM_FIELD);
        //this.deamonField = ConfigUtils.getBoolean(configJson, ATTR_DEAMON_FIELD);
        this.defaultWidth = ConfigUtils.getInteger(configJson, ATTR_DEFAULT_WIDTH);
        ConfigUtils.consume(configJson, XDynamicValueConfig.ATTR, (v) -> DynamicValueConfigFactory.newInstance((JsonObject) v));
    }

    @Override
    public String getType() {
        return this.getClass().getAnnotation(ObjectFieldConfigClass.class).name();
    }

    /*@Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/

    @Override
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public Boolean getPrimaryField() {
        return primaryField;
    }

    public void setPrimaryField(Boolean primaryField) {
        this.primaryField = primaryField;
    }

    public Boolean getAutoGen() {
        return autoGen;
    }

    public void setAutoGen(Boolean autoGen) {
        this.autoGen = autoGen;
    }

    @Override
    public Boolean getNameField() {
        return nameField;
    }

    public void setNameField(Boolean nameField) {
        this.nameField = nameField;
    }

    public Boolean getSystemField() {
        return systemField;
    }

    public void setSystemField(Boolean systemField) {
        this.systemField = systemField;
    }

    /*public Boolean getDeamonField() {
        return deamonField;
    }

    public void setDeamonField(Boolean deamonField) {
        this.deamonField = deamonField;
    }*/

    public Integer getDefaultWidth() {
        return defaultWidth;
    }

    public void setDefaultWidth(Integer defaultWidth) {
        this.defaultWidth = defaultWidth;
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
        ConfigUtils.put(configJson, ATTR_COLUMN_NAME, this.columnName);
        ConfigUtils.put(configJson, ATTR_PRIMARY_FIELD, this.primaryField);
        ConfigUtils.put(configJson, ATTR_AUTO_GEN, this.autoGen);
        ConfigUtils.put(configJson, ATTR_NAME_FIELD, this.nameField);
        ConfigUtils.put(configJson, ATTR_SYSTEM_FIELD, this.systemField);
        //ConfigUtils.putIfNotNull(configJson, ATTR_DEAMON_FIELD, this.deamonField);
        ConfigUtils.put(configJson, ATTR_DEFAULT_WIDTH, this.defaultWidth);

        // 将数据格式的属性添加到当前字段上
        if (this.valueType != null) {
            JsonObject<String, Object> valueTypeConfigJson = this.valueType.toJson();
            valueTypeConfigJson.forEach((k, v) -> {
                if (!XTypedConfig.ATTR.equalsIgnoreCase(k)) { // 避免数据格式的类型覆盖字段类型
                    configJson.put(k, v);
                }
            });
        }

        ConfigUtils.putJsonObject(configJson, XDynamicValueConfig.ATTR, this.dynamicValue);

        return configJson;
    }

}
