package io.github.cloudstars.lowcode.bpm.commons.visitor;

import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.AbstractGatewayNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.ConditionBranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.GatewayNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.loop.LoopBranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.service.ServiceTaskNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.start.StartNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserApproveTaskNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserWriteTaskNodeConfig;

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
    /*default boolean visit(NodeConfig nodeConfig) {
        return true;
    }*/

    /**
     * 结束访问 抽象节点
     *
     * @param nodeConfig 抽象节点配置
     */
    /*default void endVisit(NodeConfig nodeConfig) {
    }*/

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
    default boolean visit(UserApproveTaskNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 用户审批节点
     *
     * @param nodeConfig 用户审批节点配置
     */
    default void endVisit(UserApproveTaskNodeConfig nodeConfig) {
    }

    /**
     * 是否允许访问 用户填写节点
     *
     * @param nodeConfig 用户填写节点配置
     * @return 是否允许访问
     */
    default boolean visit(UserWriteTaskNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 用户填写节点
     *
     * @param nodeConfig 用户填写节点配置
     */
    default void endVisit(UserWriteTaskNodeConfig nodeConfig) {
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
     * 是否允许访问 循环分支节点
     *
     * @param nodeConfig 循环分支节点配置
     * @return 是否允许访问
     */
    default boolean visit(LoopBranchNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 循环分支节点
     *
     * @param nodeConfig 循环分支节点配置
     */
    default void endVisit(LoopBranchNodeConfig nodeConfig) {
    }

    /**
     * 是否允许访问 抽象网关节点
     *
     * @param nodeConfig 抽象网关节点配置
     * @return 是否允许访问
     */
    default boolean visit(AbstractGatewayNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 抽象网关节点
     *
     * @param nodeConfig 抽象网关节点配置
     */
    default void endVisit(AbstractGatewayNodeConfig nodeConfig) {
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
    default boolean visit(ConditionBranchNodeConfig nodeConfig) {
        return true;
    }

    /**
     * 结束访问 网关分支节点
     *
     * @param nodeConfig 网关分支节点配置
     */
    default void endVisit(ConditionBranchNodeConfig nodeConfig) {
    }

}
