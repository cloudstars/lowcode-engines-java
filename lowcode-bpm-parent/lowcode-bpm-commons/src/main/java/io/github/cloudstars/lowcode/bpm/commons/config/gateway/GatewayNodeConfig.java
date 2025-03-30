package io.github.cloudstars.lowcode.bpm.commons.config.gateway;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;

import java.util.List;

/**
 * 网关节点配置
 *
 * @author clouds
 */
public class GatewayNodeConfig extends AbstractNodeConfig {

    /**
     * 网关类型
     */
    private GatewayType gatewayType;

    /**
     * 网关下的分支
     */
    private List<BranchNodeConfig> branches;

    public GatewayType getGatewayType() {
        return gatewayType;
    }

    public void setGatewayType(GatewayType gatewayType) {
        this.gatewayType = gatewayType;
    }

    @Override
    protected void accept0(BpmNodeVisitor visitor) {
        for (BranchNodeConfig branch : branches) {
            branch.accept(visitor);
        }
    }

    @Override
    protected NodeTypeEnum getType() {
        return NodeTypeEnum.GATEWAY;
    }

}
