package io.github.cloudstars.lowcode.bpm.editor.config.gateway;

import io.github.cloudstars.lowcode.bpm.editor.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;
import io.github.cloudstars.lowcode.bpm.editor.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.editor.visitor.BpmNodeVisitor;

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
    }

    @Override
    protected NodeType getType() {
        return NodeType.GATEWAY;
    }

}
