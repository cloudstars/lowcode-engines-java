package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数据格式配置接口
 *
 * @author clouds 
 */
public interface XValueTypeConfig<V> extends XConfig {

    // 数据格式属性名称
    String ATTR = "valueType";

    /**
     * 获取数据格式对应的数据类型
     *
     * @return
     */
    DataTypeEnum getDataType();

    /**
     * 是否必填
     *
     * @return
     */
    @Deprecated/* 放到API参数定义以及模型字段定义中 */
    default boolean isRequired() {
        return false;
    }

    /**
     * 获取备注信息
     *
     * @return
     */
    @Deprecated/* 放到API参数定义以及模型字段定义中 */
    default String getRemark() {
        return null;
    }

    /**
     * 获取默认值（没填写的时候使用）
     *
     * @return
     */
    @Deprecated/* 放到API参数定义以及模型字段定义中 */
    default V getDefaultValue() {
        return null;
    }

    /**
     * 解析默认值
     *
     * @param configJson 默认值配置
     * @return 解析后的默认值
     */
    V parseDefaultValue(JsonObject configJson);

    /**
     * 解析目标数值（会添加默认值的逻辑）
     *
     * @param value
     * @return
     */
    default V parseTargetValue(Object value) {
        if (value == null) {
            return this.getDefaultValue();
        }

        return this.parseNonNullValue(value);
    }

    /**
     * 解析数值为正确的类型
     *
     * @param nonNullValue 非空的数值
     * @return 预期类型的数值对象
     */
    V parseNonNullValue(Object nonNullValue);

    /**
     * 校验一个非null的数据
     *
     * @param nonNullValue
     * @throws InvalidDataException
     */
    void validate(V nonNullValue) throws InvalidDataException;

}
