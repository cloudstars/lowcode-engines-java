package io.github.cloudstars.lowcode.object.commons.field.property;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

/**
 * 字段的属性（当字段需要拆子属性存储时需要定义）
 *
 * @author clouds
 */
public class FieldPropertyConfigImpl extends AbstractConfig implements XFieldPropertyConfig {

    /**
     * 本属性归属的字段
     */
    private String owner;

    /**
     * 字段属性的名称
     */
    private String name;

    /**
     * 字段的列的名称
     */
    private String columnName;

    /**
     * 字段属性的数据格式
     */
    private XValueTypeConfig valueType;

    public FieldPropertyConfigImpl() {
    }

    public FieldPropertyConfigImpl(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    @Override
    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
