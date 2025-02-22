package net.cf.object.engine.def.field;

import com.alibaba.fastjson.annotation.JSONCreator;
import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.ObjectRefType;

import java.util.List;

/**
 * 模型字段定义
 *
 * @author clouds
 */
public class FieldDef {

    /**
     * 类型
     */
    private final String type;

    /**
     * 名称
     */
    private String desc;

    /**
     * 名称
     */
    private String name;

    /**
     * 列的名称
     */
    private String columnName;

    /**
     * 是否自动生成
     */
    private boolean autoGen;

    /**
     * 是否数组
     */
    private boolean isArray;

    /**
     * 数据类型
     */
    private DataType dataType;

    /**
     * 数据长度
     */
    private Integer dataLength;

    /**
     * 数据精度
     */
    private Integer dataPrecision;

    /**
     * 默认值
     */
    private Object defaultValue;

    /**
     * 日期格式
     */
    private String dateFormat;

    /**
     * 字段的子属性列表
     */
    private List<PropertyDef> properties;

    private String refObjectName;

    private ObjectRefType refType;

    private boolean isMultiRef;


    @JSONCreator
    public FieldDef(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean isAutoGen() {
        return autoGen;
    }

    public void setAutoGen(boolean autoGen) {
        this.autoGen = autoGen;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnName() {
        return columnName != null ? columnName : this.name;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getDataPrecision() {
        return dataPrecision;
    }

    public void setDataPrecision(Integer dataPrecision) {
        this.dataPrecision = dataPrecision;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<PropertyDef> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDef> properties) {
        this.properties = properties;
    }

    public String getRefObjectName() {
        return refObjectName;
    }

    public void setRefObjectName(String refObjectName) {
        this.refObjectName = refObjectName;
    }

    public ObjectRefType getRefType() {
        return refType;
    }

    public void setRefType(ObjectRefType refType) {
        this.refType = refType;
    }

    public boolean isMultiRef() {
        return this.refType == ObjectRefType.DETAIL || isMultiRef;
    }

    public void setMultiRef(boolean multiRef) {
        isMultiRef = multiRef;
    }
}
