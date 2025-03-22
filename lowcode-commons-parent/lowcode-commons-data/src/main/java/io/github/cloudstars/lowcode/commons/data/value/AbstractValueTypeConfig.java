package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的数据格式配置
 *
 * @author clouds
 */
public abstract class AbstractValueTypeConfig<V> implements ValueTypeConfig {

    /**
     * 是否必填
     */
    protected boolean required;

    /**
     * 缺省值（数据没填的时候使用）
     */
    protected V defaultValue;

    /**
     * 说明
     */
    protected String remark;

    public AbstractValueTypeConfig() {
    }

    public AbstractValueTypeConfig(JsonObject configJson) {
        this.required = (Boolean) configJson.getOrDefault("required", false);
        this.remark = (String) configJson.getOrDefault("remark", null);
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
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
    public V getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(V defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 解析默认值
     *
     * @param configJson
     */
    @Override
    public V parseDefaultValue(JsonObject configJson) {
        Object defaultValueConfig = configJson.get("defaultValue");
        return this.parseDefaultValue(defaultValueConfig);
    }

    /**
     * 解析默认值
     *
     * @param defaultValueConfig 默认值配置
     * @return 解析后的默认值
     */
    protected abstract V parseDefaultValue(Object defaultValueConfig);

}
