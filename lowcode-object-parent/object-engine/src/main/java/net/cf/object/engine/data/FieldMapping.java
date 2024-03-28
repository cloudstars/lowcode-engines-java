package net.cf.object.engine.data;

import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.ValueType;

import java.util.ArrayList;
import java.util.List;

/**
 * 字段值映射
 *
 * @author clouds
 */
public class FieldMapping {

    /**
     * 引擎层的字段名称
     */
    private final String fieldName;

    /**
     * 驱动层的列名称
     */
    private final String columnName;

    /**
     * 字段的值类型时
     */
    private ValueType valueType;

    /**
     * 子属性列表
     */
    private final List<FieldMapping> subFields = new ArrayList<>();

    public FieldMapping(String fieldName) {
        this.fieldName = fieldName;
        this.columnName = fieldName;
    }

    public FieldMapping(String fieldName, String columnName) {
        this.fieldName = fieldName;
        this.columnName = columnName;
    }

    public FieldMapping(String fieldName, String columnName, DataType dataType) {
        this.fieldName = fieldName;
        this.columnName = columnName;
        this.valueType = new ValueType(dataType);
    }

    public FieldMapping(String fieldName, DataType dataType) {
        this.fieldName = fieldName;
        this.columnName = fieldName;
        this.valueType = new ValueType(dataType);
    }

    public FieldMapping(String fieldName, ValueType valueType) {
        this.fieldName = fieldName;
        this.columnName = fieldName;
        this.valueType = valueType;
    }

    public FieldMapping(String fieldName, DataType dataType, boolean isArray) {
        this.fieldName = fieldName;
        this.columnName = fieldName;
        this.valueType = new ValueType(dataType, isArray);
    }

    public FieldMapping(String fieldName, String columnName, DataType dataType, boolean isArray) {
        this.fieldName = fieldName;
        this.columnName = columnName;
        this.valueType = new ValueType(dataType, isArray);
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getColumnName() {
        return columnName;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public List<FieldMapping> getSubFields() {
        return subFields;
    }

    public void addSubField(FieldMapping fieldMapping) {
        this.subFields.add(fieldMapping);
    }

    public void addSubFields(List<FieldMapping> fieldMappings) {
        this.subFields.addAll(fieldMappings);
    }

}
