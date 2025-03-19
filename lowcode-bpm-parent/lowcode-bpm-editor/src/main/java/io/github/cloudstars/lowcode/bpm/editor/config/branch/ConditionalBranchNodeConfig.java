package io.github.cloudstars.lowcode.bpm.editor.config.branch;

import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;
import io.github.cloudstars.lowcode.bpm.editor.visitor.BpmNodeVisitor;

/**
 * 条件分支节点
 *
 * @author clouds
 */
public class ConditionalBranchNodeConfig extends BranchNodeConfig {

    /**
     * 进入条件
     */
    private ConditionConfig condition;

    @Override
    protected void accept0(BpmNodeVisitor visitor) {

    }

    @Override
    protected NodeType getType() {
        return NodeType.BRANCH_CONDITION;
    }

}
