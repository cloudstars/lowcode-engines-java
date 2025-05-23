package io.github.cloudstars.lowcode.bpm.commons.visitor;

import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.start.StartNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserApproveTaskNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserWriteTaskNodeConfig;

/**
 * 流程配置打印访问器
 *
 * @author clouds
 */
public class ProcessConfigOutputVisitor implements BpmNodeVisitor {

    private StringBuilder builder;

    public ProcessConfigOutputVisitor(StringBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(StartNodeConfig nodeConfig) {
        this.builder.append("Start: ").append(nodeConfig.getKey());
        return true;
    }

    @Override
    public void endVisit(StartNodeConfig nodeConfig) {
    }

    @Override
    public boolean visit(EndNodeConfig nodeConfig) {
        this.builder.append("End: ").append(nodeConfig.getKey());
        return true;
    }

    @Override
    public void endVisit(EndNodeConfig nodeConfig) {
    }

    @Override
    public boolean visit(UserApproveTaskNodeConfig nodeConfig) {
        this.builder.append("User Approve: ").append(nodeConfig.getKey());
        return true;
    }

    @Override
    public void endVisit(UserApproveTaskNodeConfig nodeConfig) {
    }

    @Override
    public boolean visit(UserWriteTaskNodeConfig nodeConfig) {
        this.builder.append("User Write: ").append(nodeConfig.getKey());
        return true;
    }

    @Override
    public void endVisit(UserWriteTaskNodeConfig nodeConfig) {
    }

}
