package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的数据格式配置
 *
 * @param <T> 数据格式的值的类型
 * @author clouds
 */
public abstract class AbstractValueTypeConfig<T> extends AbstractConfig implements ValueTypeConfig<T> {

    /**
     * 数据格式的名称
     */
    protected String dataType;

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
        this.dataType = this.getClass().getAnnotationsByType(ValueTypeConfigClass.class)[0].name();
    }

    public AbstractValueTypeConfig(JsonObject configJson) {
        this();
        this.required = (Boolean) configJson.getOrDefault("required", false);
        this.remark = (String) configJson.getOrDefault("remark", null);
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        json.put("dataType", this.dataType);
        json.put("required", this.isRequired());
        json.put("remark", this.getRemark());
        json.put("defaultValue", this.getDefaultValue());

        return json;
    }

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

}
