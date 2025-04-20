package io.github.cloudstars.lowcode.commons.value.type;


import io.github.cloudstars.lowcode.commons.value.type.config.XValueTypeConfig;

/**
 * 数据格式
 *
 * @author clouds
 */
public abstract class AbstractValueTypeImpl<VC extends XValueTypeConfig, V extends Object> implements XValueType<V> {

    /**
     * 数据格式配置
     */
    protected VC valueTypeConfig;

    protected AbstractValueTypeImpl(VC valueTypeConfig) {
        this.valueTypeConfig = valueTypeConfig;
    }

    @Override
    public DataTypeEnum getDataType() {
        return this.valueTypeConfig.getDataType();
    }


}
