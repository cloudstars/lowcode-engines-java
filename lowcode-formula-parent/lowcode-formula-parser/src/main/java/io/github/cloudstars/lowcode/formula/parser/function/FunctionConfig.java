package io.github.cloudstars.lowcode.formula.parser.function;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;

import java.util.List;

/**
 * 函数配置
 *
 * @author clouds
 */
public class FunctionConfig extends AbstractConfig {

    /**
     * 名称
     */
    private String name;

    /**
     * 参数列表
     */
    private List<FunctionParam> params;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FunctionParam> getParams() {
        return params;
    }

    public void setParams(List<FunctionParam> params) {
        this.params = params;
    }
}
