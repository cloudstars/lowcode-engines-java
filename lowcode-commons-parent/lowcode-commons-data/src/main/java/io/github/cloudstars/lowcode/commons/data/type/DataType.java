package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.editor.XConfig;

/**
 * 数据格式接口
 *
 * @author clouds
 */
public interface DataType extends XConfig {

    /**
     * 获取数据格式的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取存储的数值类型
     *
     * @return
     */
    StoreValueType getDbValueType();

    /**
     * 校验数据
     *
     * @param data
     * @throws InvalidDataException
     */
    void validate(Object data) throws InvalidDataException;

}
