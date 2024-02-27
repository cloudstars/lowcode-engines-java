package net.cf.object.engine.def.field;

import net.cf.object.engine.def.fieldtype.AttributeDescriptor;
import net.cf.object.engine.object.DataType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 编号
     */
    private String key;

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

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
     * 默认值配置
     */
    private DefaultValueConfig defaultValueConfig;

    /**
     * 日期格式
     */
    private String dateFormat;

    /**
     * 字段的数据子属性
     */
    private List<FieldPropertyTestImpl> properties;

    /**
     * 字段的个性化属性
     */
    //private List<FieldAttributeTestImpl> attributes;

    /**
     * 字段的个性化属性的值映射
     */
    private final Map<String, Object> attrValueMap = new HashMap<>();

    public FieldDef(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public DefaultValueConfig getDefaultValueConfig() {
        return defaultValueConfig;
    }

    public void setDefaultValueConfig(DefaultValueConfig defaultValueConfig) {
        this.defaultValueConfig = defaultValueConfig;
    }

    public List<FieldPropertyTestImpl> getProperties() {
        return properties;
    }

    public void setProperties(List<FieldPropertyTestImpl> properties) {
        this.properties = properties;
    }

    /*public List<FieldAttributeTestImpl> getAttributes() {
        return attributes;
    }*/

    public void setAttributes(List<FieldAttribute> attributes) {
        //this.attributes = attributes;
        for (FieldAttribute attribute : attributes) {
            String attrCode = attribute.getCode();
            Object attrValue = attribute.getValue();
            if (attrValue == null) {
                AttributeDescriptor attributeDescriptor = attribute.getDescriptor();
                if (attributeDescriptor != null) {
                    attrValue = attributeDescriptor.getDefaultValue();
                }
            }
            this.attrValueMap.put(attrCode, attrValue);
        }
    }

    /**
     * 获取字段的个性化属性值映射
     *
     * @return
     */
    public Map<String, Object> getAttributeValueMap() {
        return attrValueMap;
    }
}
