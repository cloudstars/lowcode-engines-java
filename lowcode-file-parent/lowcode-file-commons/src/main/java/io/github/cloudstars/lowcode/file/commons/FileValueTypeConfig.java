package io.github.cloudstars.lowcode.file.commons;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.data.valuetype.AbstractObjectValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.ValueTypeConfigClass;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;

import java.util.Map;

/**
 * 文件数据格式配置
 *
 * @author clouds 
 */
@ValueTypeConfigClass(name = "FILE")
public class FileValueTypeConfig extends AbstractObjectValueTypeConfig<FileObject> {

    /**
     * 数据中的文件标识属性名
     */
    private final static String ATTR_KEY = "key";

    /**
     * 数据中的文件名称属性名
     */
    private final static String ATTR_NAME = "name";

    public FileValueTypeConfig(JsonObject configJson) {
        super(configJson);

        // 默认值需要在所有属性解析完之后再解析
        this.defaultValue = this.parseDefaultValue(configJson);
    }

    @Override
    protected FileObject parseDefaultValue(Object defaultValueConfig) {
        FileObject defaultValue = null;
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof JsonObject || defaultValueConfig instanceof Map) {
                Map<String, Object> valueMap = (Map<String, Object>) defaultValueConfig;
                defaultValue = new FileObject();
                defaultValue.setKey((String) valueMap.get(ATTR_KEY));
                defaultValue.setName((String) valueMap.get(ATTR_NAME));
            } else {
                throw new InvalidDataException("文件数据格式不正确，请检查您的数据：" + JsonUtils.toJsonString(defaultValueConfig));
            }
        }

        return defaultValue;
    }

    @Override
    public FileObject parseNonNullValue(Object nonNullValue) {
        if (!(nonNullValue instanceof Map)) {
            throw new InvalidDataException("文件类型的数据格式不正确，必须是包括key、name属性的对象");
        }

        Map<String, Object> fileValueMap = (Map<String, Object>) nonNullValue;
        FileObject fileObject = new FileObject();
        fileObject.setKey((String) fileValueMap.get(ATTR_KEY));
        fileObject.setName((String) fileValueMap.get(ATTR_NAME));

        return fileObject;
    }


    @Override
    public void validateNonNullValue(FileObject nonNullValue) throws InvalidDataException {

    }

}
