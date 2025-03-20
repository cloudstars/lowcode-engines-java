package io.github.cloudstars.lowcode.commons.data.value.custom;

import io.github.cloudstars.lowcode.commons.data.value.StoreValueType;
import io.github.cloudstars.lowcode.commons.data.value.AbstractValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.value.DataTypeConfigClass;
import io.github.cloudstars.lowcode.commons.data.value.ObjectProperty;
import io.github.cloudstars.lowcode.commons.lang.value.InvalidValueFormatException;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;
import io.github.cloudstars.lowcode.commons.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件数据格式配置
 *
 * @author clouds 
 */
@DataTypeConfigClass(name = "FILE")
public class FileValueTypeConfig extends AbstractValueTypeConfig<FileValue> {


    /**
     * 数据中的文件标识属性名
     */
    private final static String KEY_ATTR = "key";

    /**
     * 数据中的文件名称属性名
     */
    private final static String NAME_ATTR = "name";

    /**
     * 文件数据格式的两个属性
     */
    private static List<ObjectProperty> FILE_PROPS = new ArrayList<>();

    static {
        //FILE_PROPS.add(new ObjectProperty("key", DataTypeClassFactory.get(BuildInDataTypeConstants.TEXT)));
        //FILE_PROPS.add(new ObjectProperty("name", DataTypeClassFactory.get(BuildInDataTypeConstants.TEXT)));
    }



    /**
     * 对象值下面的属性列表
     */
    private List<ObjectProperty> properties;

    public FileValueTypeConfig(JsonObject configJson) {
        super(configJson);

        // 默认值需要在所有属性解析完之后再解析
        this.defaultValue = this.parseDefaultValue(configJson);
    }

    @Override
    protected FileValue parseDefaultValue(Object defaultValueConfig) {
        FileValue defaultValue = null;
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof JsonObject || defaultValueConfig instanceof Map) {
                Map<String, Object> valueMap = (Map<String, Object>) defaultValueConfig;
                defaultValue = new FileValue();
                defaultValue.setKey((String) valueMap.get(KEY_ATTR));
                defaultValue.setName((String) valueMap.get(NAME_ATTR));
            } else {
                throw new InvalidValueFormatException("文件数据格式不正确，请检查您的数据：" + JsonUtils.toJsonString(defaultValueConfig));
            }
        }

        return defaultValue;
    }

    @Override
    public FileValue parseNonNullValue(Object nonNullValue) {
        if (!(nonNullValue instanceof Map)) {
            throw new InvalidValueFormatException("文件类型的数据格式不正确，必须是包括key、name属性的对象");
        }

        Map<String, Object> fileValueMap = (Map<String, Object>) nonNullValue;
        FileValue fileObject = new FileValue();
        fileObject.setKey((String) fileValueMap.get(KEY_ATTR));
        fileObject.setName((String) fileValueMap.get(NAME_ATTR));

        return fileObject;
    }

    @Override
    public StoreValueType getStoreDataType() {
        return StoreValueType.OBJECT;
    }

    public List<ObjectProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ObjectProperty> properties) {
        this.properties = properties;
    }

}
