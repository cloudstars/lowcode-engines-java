package io.github.cloudstars.lowcode.file.commons;

import io.github.cloudstars.lowcode.commons.value.type.AbstractValueTypeImpl;
import io.github.cloudstars.lowcode.commons.value.type.InvalidDataException;

import java.util.Map;

public class FileValueType extends AbstractValueTypeImpl<FileValueTypeConfig, FileObject> {

    /**
     * 数据中的文件标识属性名
     */
    private final static String ATTR_KEY = "key";

    /**
     * 数据中的文件名称属性名
     */
    private final static String ATTR_NAME = "name";


    public FileValueType(FileValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public FileObject parseValue(Object rawValue) throws InvalidDataException {
        if (!(rawValue instanceof Map)) {
            throw new InvalidDataException("文件类型的数据格式不正确，必须是包括key、name属性的对象");
        }

        Map<String, Object> fileValueMap = (Map<String, Object>) rawValue;
        FileObject fileObject = new FileObject();
        fileObject.setKey((String) fileValueMap.get(ATTR_KEY));
        fileObject.setName((String) fileValueMap.get(ATTR_NAME));

        return fileObject;
    }

    @Override
    public void validate(FileObject value) throws InvalidDataException {

    }

}
