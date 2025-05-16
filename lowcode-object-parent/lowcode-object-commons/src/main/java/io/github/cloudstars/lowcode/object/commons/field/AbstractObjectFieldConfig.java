package io.github.cloudstars.lowcode.object.commons.field;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
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

    private static final String ATTR_COLUMN_NAME = "columnName";

    private static final String ATTR_AUTO_GEN = "autoGen";

    private static final String ATTR_DEFAULT_WIDTH = "defaultWidth";

    private static final String ATTR_SYSTEM_FIELD = "systemField";

    /**
     * 字段标题
     */
    private String title;

    /**
     * 字段的列名
     */
    private String columnName;

    /**
     * 默认宽度
     */
    private Integer defaultWidth;

    /**
     * 是否系统字段
     */
    private Boolean systemField;

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
    }

    protected AbstractObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        this.title = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_LABEL);
        ConfigUtils.consumeIfPresent(configJson, ATTR_COLUMN_NAME, (v) -> {
            this.columnName = (String) v;
        });
        this.defaultWidth = ConfigUtils.getInteger(configJson, ATTR_DEFAULT_WIDTH);
        this.systemField = ConfigUtils.getBoolean(configJson, ATTR_SYSTEM_FIELD);
        ConfigUtils.consumeIfPresent(configJson, ATTR_AUTO_GEN, (v) -> {
            this.autoGen = (Boolean) v;
        });
        JsonObject dynamicValueConfigJson = (JsonObject) configJson.get(XDynamicValueConfig.ATTR);
        if (dynamicValueConfigJson != null) {
            this.dynamicValue = DynamicValueConfigFactory.newInstance(dynamicValueConfigJson);
        }
    }

    @Override
    public String getType() {
        return this.getClass().getAnnotation(ObjectFieldConfigClass.class).name();
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getDefaultWidth() {
        return defaultWidth;
    }

    public void setDefaultWidth(Integer defaultWidth) {
        this.defaultWidth = defaultWidth;
    }

    public Boolean getSystemField() {
        return systemField;
    }

    public void setSystemField(Boolean systemField) {
        this.systemField = systemField;
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
        ConfigUtils.put(configJson, GlobalAttrNames.ATTR_TITLE, this.title);
        ConfigUtils.putIfNotNull(configJson, ATTR_DEFAULT_WIDTH, this.defaultWidth);
        ConfigUtils.putIfNotNull(configJson, ATTR_SYSTEM_FIELD, this.systemField);
        ConfigUtils.putIfNotNull(configJson, ATTR_COLUMN_NAME, this.columnName);
        ConfigUtils.putIfNotNull(configJson, ATTR_AUTO_GEN, this.autoGen);

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
