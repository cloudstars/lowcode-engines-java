package io.github.cloudstars.lowcode.commons.data.type.custom;

import io.github.cloudstars.lowcode.commons.data.type.DataProperty;
import io.github.cloudstars.lowcode.commons.data.type.ObjectDataType;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件数据格式，包含 key、name 两个属性
 *
 * @author clouds
 */
public class FileValueType extends ObjectDataType {

    /**
     * 文件数据格式的两个属性
     */
    private static List<DataProperty> FILE_PROPS = new ArrayList<>();

    static {
        //FILE_PROPS.add(new DataProperty("key", DataTypeClassFactory.get(BuildInDataTypeConstants.TEXT)));
        //FILE_PROPS.add(new DataProperty("name", DataTypeClassFactory.get(BuildInDataTypeConstants.TEXT)));
    }

    public FileValueType() {
        super(FILE_PROPS);
    }

    @Override
    public String getName() {
        return "FILE";
    }

}
