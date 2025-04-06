package io.github.cloudstars.lowcode.bpm.commons.config;

/**
 * BPM节点类型枚举
 *
 * @author clouds
 */
public enum NodeTypeEnum {

    START,
    END,
    USER_APPROVE,
    USER_WRITE,
    SERVICE,
    LOOP,
    GATEWAY,
    BRANCH,
    BRANCH_CONDITION,
    BRANCH_ITERATOR,
    CONDITION;

}
