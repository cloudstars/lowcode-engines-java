package io.github.cloudstars.lowcode.formula.parser.function;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

/**
 * 函数参数
 *
 * @author clouds
 */
public class FunctionParam extends AbstractConfig {

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数的数据格式
     */
    private XValueTypeConfig valueTypeConfig;

    /**
     * 是否必填
     */
    private boolean required;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public XValueTypeConfig getValueTypeConfig() {
        return valueTypeConfig;
    }

    public void setValueTypeConfig(XValueTypeConfig valueTypeConfig) {
        this.valueTypeConfig = valueTypeConfig;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
