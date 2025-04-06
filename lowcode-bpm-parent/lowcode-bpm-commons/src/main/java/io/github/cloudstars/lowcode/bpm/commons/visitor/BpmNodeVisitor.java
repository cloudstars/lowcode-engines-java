package io.github.cloudstars.lowcode.bpm.commons.visitor;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.GatewayBranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.GatewayNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.service.ServiceTaskNodeConfig;
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
     * 是否允许访问 抽象节点
     *
     * @param nodeConfig 抽象节点配置
     * @return 是否允许访问
     */
    default boolean visit(AbstractNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 抽象节点
     *
     * @param nodeConfig 抽象节点配置
     */
    default void endVisit(AbstractNodeConfig nodeConfig) {
    }

    /**
     * 是否允许访问 开始节点
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
     * @param nodeConfig 开始节点配置
     */
    default void endVisit(StartNodeConfig nodeConfig) {
    }

    /**
     * 是否允许访问 结束节点
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
     * 是否允许访问 用户审批节点
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
     * 是否允许访问 用户填写节点
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

    /**
     * 是否允许访问 程序节点
     *
     * @param nodeConfig 用户填写节点配置
     * @return 是否允许访问
     */
    default boolean visit(ServiceTaskNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 用户填写节点
     *
     * @param nodeConfig 用户填写节点配置
     */
    default void endVisit(ServiceTaskNodeConfig nodeConfig) {
    }
    
    /**
     * 是否允许访问 网关节点
     *
     * @param nodeConfig 网关节点配置
     * @return 是否允许访问
     */
    default boolean visit(GatewayNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 网关节点
     *
     * @param nodeConfig 网关节点配置
     */
    default void endVisit(GatewayNodeConfig nodeConfig) {
    }

    /**
     * 是否允许访问 网关分支节点
     *
     * @param nodeConfig 网关分支节点配置
     * @return 是否允许访问
     */
    default boolean visit(GatewayBranchNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 网关分支节点
     *
     * @param nodeConfig 网关分支节点配置
     */
    default void endVisit(GatewayBranchNodeConfig nodeConfig) {
    }

}
