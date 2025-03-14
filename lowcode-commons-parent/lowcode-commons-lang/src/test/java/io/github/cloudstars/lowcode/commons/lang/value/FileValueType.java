package io.github.cloudstars.lowcode.commons.lang.value;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件数据格式，包含 key、name 两个属性
 *
 * @author clouds
 */
public class FileValueType extends ObjectValueType {

    /**
     * 文件数据格式的两个属性
     */
    private static List<ObjectProperty> FILE_PROPS = new ArrayList<>();
    static {
        FILE_PROPS.add(new ObjectProperty("key", new TextValueType(false)));
        FILE_PROPS.add(new ObjectProperty("name", new TextValueType(false)));
    }

    public FileValueType() {
        super(FILE_PROPS);
    }

    public FileValueType(boolean isArray) {
        super(FILE_PROPS);
        this.isArray = isArray;
    }

    @Override
    protected void validateNonNullValue(Object value) throws InvalidDataFormatException {
    }

}
