package io.github.cloudstars.lowcode.commons.data.type.custom;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.data.type.AbstractObjectDataTypeConfig;
import io.github.cloudstars.lowcode.commons.data.type.DataTypeConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.lang.value.InvalidValueFormatException;

import java.util.Map;

/**
 * 文件数据格式配置
 *
 * @author clouds 
 */
@DataTypeConfigClass(name = "FILE")
public class FileDataTypeConfig extends AbstractObjectDataTypeConfig<FileValue> {

    /**
     * 数据中的文件标识属性名
     */
    private final static String KEY_ATTR = "key";

    /**
     * 数据中的文件名称属性名
     */
    private final static String NAME_ATTR = "name";

    public FileDataTypeConfig(JsonObject configJson) {
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
    public void validate(FileValue nonNullValue) throws InvalidDataException {

    }

}
