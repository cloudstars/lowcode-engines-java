package io.github.cloudstars.lowcode.bpm.editor.config;

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
    GATEWAY_XOR,
    GATEWAY_OR,
    GATEWAY_AND,
    CONDITION;

}
