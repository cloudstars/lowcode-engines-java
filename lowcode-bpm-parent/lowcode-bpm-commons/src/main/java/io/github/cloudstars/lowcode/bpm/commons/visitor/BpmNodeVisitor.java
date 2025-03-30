package io.github.cloudstars.lowcode.bpm.commons.visitor;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.start.StartNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserApproveNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserWriteNodeConfig;

/**
 * BPM节点访问器
 *
 * @author clouds
 */
public interface BpmNodeVisitor {

    /**
     * 是否允许访问 开始结点
     *
     * @param nodeConfig 开始节点配置
     * @return 是否允许访问
     */
    default boolean visit(StartNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 开始节点
     *
     * @param nodeConfig 结束节点配置
     */
    default void endVisit(StartNodeConfig nodeConfig) {
    }

    /**
     * 是否允许访问 结束结点
     *
     * @param nodeConfig 结束节点配置
     * @return 是否允许访问
     */
    default boolean visit(EndNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 结束节点
     *
     * @param nodeConfig 结束节点配置
     */
    default void endVisit(EndNodeConfig nodeConfig) {
    }

    /**
     * 是否允许访问 用户审批结点
     *
     * @param nodeConfig 用户审批节点配置
     * @return 是否允许访问
     */
    default boolean visit(UserApproveNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 用户审批节点
     *
     * @param nodeConfig 用户审批节点配置
     */
    default void endVisit(UserApproveNodeConfig nodeConfig) {
    }

    /**
     * 是否允许访问 用户填写结点
     *
     * @param nodeConfig 用户填写节点配置
     * @return 是否允许访问
     */
    default boolean visit(UserWriteNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 用户填写节点
     *
     * @param nodeConfig 用户填写节点配置
     */
    default void endVisit(UserWriteNodeConfig nodeConfig) {
    }

}
