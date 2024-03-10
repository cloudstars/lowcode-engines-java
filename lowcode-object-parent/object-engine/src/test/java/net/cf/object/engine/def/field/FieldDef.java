package net.cf.object.engine.def.field;

import com.alibaba.fastjson.annotation.JSONCreator;
import net.cf.object.engine.object.DataType;

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
     * 是否自动生成的ID
     */
    private boolean isAuto;

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 是否集合
     */
    private boolean isCollection;

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
    private List<FieldPropertyDef> properties;

    /**
     * 字段的子属定义映射
     */
    // private Map<String, FieldPropertyDef> propertyMap;

    /**
     * 字段的个性化属性的值映射
     */
    //private final Map<String, Object> attrValueMap = new HashMap<>();

    @JSONCreator
    public FieldDef(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColumnName() {
        return columnName != null ? columnName : this.code;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
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

    public List<FieldPropertyDef> getProperties() {
        return properties;
    }

    public void setProperties(List<FieldPropertyDef> properties) {
        this.properties = properties;
    }

    /*public void setProperties(List<FieldPropertyDef> properties) {
        this.properties = properties;
        if (properties == null) {
            this.propertyMap = null;
        } else {
            this.propertyMap = new HashMap<>();
            for (FieldPropertyDef property : properties) {
                this.propertyMap.put(property.getCode(), property);
            }
        }
    }*/

    /*public void setAttributes(List<FieldAttribute> attributes) {
        for (FieldAttribute attribute : attributes) {
            String attrCode = attribute.getCode();
            Object attrValue = attribute.getValue();
            this.attrValueMap.put(attrCode, attrValue);
        }
    }*/

    /**
     * 获取字段的个性化属性值映射
     *
     * @return
     */
    /*public Map<String, Object> getAttrValueMap() {
        return attrValueMap;
    }*/
}
