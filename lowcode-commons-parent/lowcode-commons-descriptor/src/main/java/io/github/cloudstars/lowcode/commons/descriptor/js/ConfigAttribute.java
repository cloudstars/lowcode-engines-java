package io.github.cloudstars.lowcode.commons.descriptor.js;

/**
 * 配置的属性接口，表示某个属性
 *
 * @author clouds
 */
public class ConfigAttribute {

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性标题
     */
    private String title;

    /**
     * 是否必须
     */
    private boolean required;

    /**
     * 属性数据类型
     */
    private AttributeDataTypeEnum dataType = AttributeDataTypeEnum.STRING;

    /**
     * 当dataType为数组时，表示数组子项的配置
     */
    private ArrayItem items;

    /**
     * 属性的默认值
     */
    private Object defaultValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public AttributeDataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(AttributeDataTypeEnum dataType) {
        this.dataType = dataType;
    }

    public ArrayItem getItems() {
        return items;
    }

    public void setItems(ArrayItem items) {
        this.items = items;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 数组下子项的配置
     *
     * @author clouds
     */
    static public class ArrayItem {
        private AttributeDataTypeEnum dataType;

        private String descriptor;

        public AttributeDataTypeEnum getDataType() {
            return dataType;
        }

        public void setDataType(AttributeDataTypeEnum dataType) {
            this.dataType = dataType;
        }

        public String getDescriptor() {
            return descriptor;
        }

        public void setDescriptor(String descriptor) {
            this.descriptor = descriptor;
        }
    }


}