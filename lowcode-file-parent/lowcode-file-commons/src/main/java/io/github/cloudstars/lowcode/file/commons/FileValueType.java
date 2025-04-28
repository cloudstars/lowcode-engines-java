package io.github.cloudstars.lowcode.file.commons;


import io.github.cloudstars.lowcode.commons.value.AbstractValueTypeImpl;
import io.github.cloudstars.lowcode.commons.value.InvalidDataException;

import java.util.Map;

public class FileValueType extends AbstractValueTypeImpl<FileValueTypeConfig, FileValue> {

    /**
     * 数据中的文件标识属性名
     */
    private static final String ATTR_KEY = "key";

    /**
     * 数据中的文件名称属性名
     */
    private static final String ATTR_NAME = "name";


    public FileValueType(FileValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public FileValue parseValue(Object rawValue) throws InvalidDataException {
        if (!(rawValue instanceof Map)) {
            throw new InvalidDataException("文件类型的数据格式不正确，必须是包括key、name属性的对象");
        }

        Map<String, Object> fileValueMap = (Map<String, Object>) rawValue;
        FileValue fileValue = new FileValue();
        fileValue.setKey((String) fileValueMap.get(ATTR_KEY));
        fileValue.setName((String) fileValueMap.get(ATTR_NAME));

        return fileValue;
    }

    @Override
    public FileValue parseDefaultValue(Object defaultValueConfig) throws io.github.cloudstars.lowcode.commons.value.InvalidDataException {
        return null;
    }

    @Override
    public void validate(FileValue value) throws InvalidDataException {

    }

}
