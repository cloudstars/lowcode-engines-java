package net.cf.object.engine.fieldtype;

import com.alibaba.fastjson.JSONObject;

/**
 * 字段开发态的配置
 *
 * @author clouds
 */
public class FieldSchema extends JSONObject {

    public String getDataType() {
        return this.getString("dataType");
    }

    public void setDataType(String dataType) {
        this.put("dataType", dataType);
    }

    public Integer getDataLength() {
        return this.getInteger("dataLength");
    }

    public void setDataLength(Integer dataLength) {
        this.put("dataLength", dataLength);
    }

    public Integer getDataPrecision() {
        return this.getInteger("dataLength");
    }

    public void setDataPrecision(Integer dataPrecision) {
        this.put("dataPrecision", dataPrecision);
    }

    public boolean isMultiple() {
        Boolean v = (Boolean) this.get("multiple");
        return v != null ? v.booleanValue() : false;
    }

    public void setMultiple(boolean multiple) {
        this.put("multiple", multiple);
    }

    public boolean isRedundant() {
        Boolean v = (Boolean) this.get("redundant");
        return v != null ? v.booleanValue() : false;
    }

    public void setRedundant(boolean redundant) {
        this.put("redundant", redundant);
    }

    public boolean isSaveAsText() {
        Boolean v = (Boolean) this.get("saveAsText");
        return v != null ? v.booleanValue() : false;
    }

    public void setSaveAsText(boolean saveAsText) {
        this.put("saveAsText", saveAsText);
    }

    public String getExpression() {
        return this.getString("expression");
    }

    public void setExpression(String expression) {
        this.put("expression", expression);
    }

    public DefaultValueConfig getDefaultValueConfig() {
        return (DefaultValueConfig) this.get("defaultValueConfig");
    }

    public void setDefaultValueConfig(DefaultValueConfig defaultValueConfig) {
        this.put("defaultValueConfig", defaultValueConfig);
    }

}
