package io.github.cloudstars.lowcode.value.loader;


import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

/**
 * 数据格式
 *
 * @author clouds
 * @param <V> 数据格式的值的类型
 */
public abstract class AbstractValueType<C extends XValueTypeConfig, V extends Object> implements XValueType<C, V> {

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

    protected AbstractValueType(C valueTypeConfig) {
        this.valueTypeConfig = valueTypeConfig;
    }

}

