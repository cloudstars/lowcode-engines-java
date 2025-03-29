package io.github.cloudstars.lowcode.bpm.commons.config.branch;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeType;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;

/**
 * 循环分支节点
 *
 * @author clouds
 */
public class IteratableBranchNodeConfig extends BranchNodeConfig {

    /**
     * 循环条件
     */
    private ConditionConfig condition;

    @Override
    protected void accept0(BpmNodeVisitor visitor) {

    }

    @Override
    protected NodeType getType() {
        return NodeType.BRANCH_ITERATOR;
    }

}
