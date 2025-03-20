package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.commons.editor.XConfig;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 数据格式配置接口
 *
 * @author clouds 
 */
public interface ValueTypeConfig<V> extends XConfig {

    /**
     * 获取存储的数据格式
     *
     * @return
     */
    StoreValueType getStoreDataType();

    /**
     * 是否必填
     *
     * @return
     */
    default boolean isRequired() {
        return false;
    }

    /**
     * 获取备注信息
     *
     * @return
     */
    default String getRemark() {
        return null;
    }

    /**
     * 获取默认值（没填写的时候使用）
     *
     * @return
     */
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

}
