package io.github.cloudstars.lowcode.commons.value;


import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 数据格式
 *
 * @author clouds
 * @param <V> 数据格式的值的类型
 */
public abstract class AbstractValueTypeImpl<C extends XValueTypeConfig<V>, V extends Object> implements XValueType<V> {

    /**
     * 数据格式配置
     */
    protected C valueTypeConfig;

    @Override
    public C getValueTypeConfig() {
        return valueTypeConfig;
    }

    public void setValueTypeConfig(C valueTypeConfig) {
        this.valueTypeConfig = valueTypeConfig;
    }

    protected AbstractValueTypeImpl(C valueTypeConfig) {
        this.valueTypeConfig = valueTypeConfig;
    }

}

