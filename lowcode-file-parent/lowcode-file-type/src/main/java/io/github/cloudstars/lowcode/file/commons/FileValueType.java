package io.github.cloudstars.lowcode.file.commons;


import io.github.cloudstars.lowcode.commons.value.AbstractValueType;
import io.github.cloudstars.lowcode.commons.value.InvalidDataException;
import io.github.cloudstars.lowcode.commons.value.ValueTypeClass;

import java.util.Map;

/**
 * 文件数据格式
 *
 * @author clouds 
 */
@ValueTypeClass(name = "FILE", valueTypeConfigClass = FileValueTypeConfig.class)
public class FileValueType extends AbstractValueType<FileValueTypeConfig, File> {

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
    public File parseDefaultValue() throws InvalidDataException {
        return null;
    }

    @Override
    public File mergeDefaultValue(Object rawValue) throws io.github.cloudstars.lowcode.commons.value.InvalidDataException {
        return null;
    }

    @Override
    public File parseValue(Object rawValue) throws InvalidDataException {
        if (!(rawValue instanceof Map)) {
            throw new InvalidDataException("文件类型的数据格式不正确，必须是包括key、name属性的对象");
        }

        Map<String, Object> fileValueMap = (Map<String, Object>) rawValue;
        File file = new File();
        file.setKey((String) fileValueMap.get(ATTR_KEY));
        file.setName((String) fileValueMap.get(ATTR_NAME));

        return file;
    }

    @Override
    public void validate(File value) throws InvalidDataException {

    }

}
