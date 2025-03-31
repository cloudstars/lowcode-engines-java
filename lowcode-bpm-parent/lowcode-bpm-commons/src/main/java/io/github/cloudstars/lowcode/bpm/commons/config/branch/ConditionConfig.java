package io.github.cloudstars.lowcode.bpm.commons.config.branch;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;

/**
 * 条件配置
 *
 * @author clouds
 */
public class ConditionConfig extends AbstractNodeConfig {

    /**
     * 条件表达式
     */
    private String expression;

    @Override
    protected void accept0(BpmNodeVisitor visitor) {

    }

    @Override
    public NodeTypeEnum getType() {
        return NodeTypeEnum.CONDITION;
    }

}
