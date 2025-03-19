package io.github.cloudstars.lowcode.bpm.editor.config.branch;

import io.github.cloudstars.lowcode.bpm.editor.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;
import io.github.cloudstars.lowcode.bpm.editor.visitor.BpmNodeVisitor;

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
    protected NodeType getType() {
        return NodeType.CONDITION;
    }

}
