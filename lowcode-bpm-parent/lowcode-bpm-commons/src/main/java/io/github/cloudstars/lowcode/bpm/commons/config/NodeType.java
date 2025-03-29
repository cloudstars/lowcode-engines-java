package io.github.cloudstars.lowcode.bpm.commons.config;

/**
 * BPM节点类型
 *
 * @author clouds
 */
public enum NodeType {

    START,
    END,
    USER_APPROVE,
    USER_WRITE,
    SERVICE,
    GATEWAY,
    BRANCH,
    BRANCH_CONDITION,
    BRANCH_ITERATOR,
    CONDITION;

}
