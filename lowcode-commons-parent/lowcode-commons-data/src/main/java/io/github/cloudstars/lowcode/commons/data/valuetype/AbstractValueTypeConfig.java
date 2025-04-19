package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的数据格式配置
 *
 * @param <T> 数据格式的值的类型
 * @author clouds
 */
public abstract class AbstractValueTypeConfig<T> extends AbstractConfig implements XValueTypeConfig<T> {

    // 数据格式类型属性
    private static final String ATTR_TYPE = "type";


    // 数据类型属性
    private static final String ATTR_DATA_TYPE = "dataType";

    // 是否必填属性
    private static final String ATTR_REQUIRED = "required";

    private String type;

    /**
     * 数据类型
     */
    // protected DataTypeEnum dataType;

    /**
     * 是否必填
     */
    protected boolean required;

    /**
     * 缺省值（数据没填的时候使用）
     */
    protected T defaultValue;

    /**
     * 说明
     */
    protected String remark;

    public AbstractValueTypeConfig() {
    }

    public AbstractValueTypeConfig(JsonObject configJson) {
        this.type = this.getClass().getAnnotation(ValueTypeConfigClass.class).name();
        this.required = (Boolean) configJson.getOrDefault(ATTR_REQUIRED, false);
        this.remark = (String) configJson.getOrDefault("remark", null);
    }

    /*@Override
    public DataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }*/

    @Override
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public T getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 解析默认值
     *
     * @param configJson
     */
    @Override
    public T parseDefaultValue(JsonObject configJson) {
        Object defaultValueConfig = configJson.get("defaultValue");
        return this.parseDefaultValue(defaultValueConfig);
    }

    /**
     * 解析默认值
     *
     * @param defaultValueConfig 默认值配置
     * @return 解析后的默认值
     */
    protected abstract T parseDefaultValue(Object defaultValueConfig);

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_TYPE, this.type);
        configJson.put(ATTR_DATA_TYPE, this.getDataType());
        configJson.put(ATTR_REQUIRED, this.isRequired());
        configJson.put("remark", this.getRemark());
        configJson.put("defaultValue", this.getDefaultValue());

        return configJson;
    }

}
