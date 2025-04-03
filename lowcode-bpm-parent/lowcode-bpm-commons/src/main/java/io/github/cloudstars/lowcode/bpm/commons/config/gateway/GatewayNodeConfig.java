package io.github.cloudstars.lowcode.bpm.commons.config.gateway;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.config.XConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 网关节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.GATEWAY.EXCLUSIVE")
public class GatewayNodeConfig extends AbstractNodeConfig {

    /**
     * 网关类型
     */
    private GatewayType gatewayType;

    /**
     * 网关下的分支列表
     */
    private List<GatewayBranchNodeConfig> branches;

    public GatewayNodeConfig() {
    }

    public GatewayNodeConfig(JsonObject configJson) {
        super(configJson);

        Object gatewayTypeValue = configJson.get("gatewayType");
        if (gatewayTypeValue != null) {
            this.gatewayType = GatewayType.valueOf((String) gatewayTypeValue);
        } else {
            this.gatewayType = GatewayType.OR;
        }

        JsonArray branchesConfig = (JsonArray) configJson.get("branches");
        this.branches = XConfigUtils.toList(branchesConfig, GatewayBranchNodeConfig.class);

        // 建立网关与分支的前后关系，网关分支节点的前后节点都是网关节点
        for (GatewayBranchNodeConfig branch : this.branches) {
            branch.setPrevNode(this);
            branch.setNextNode(this);
        }

    }

    public GatewayType getGatewayType() {
        return gatewayType;
    }

    public List<GatewayBranchNodeConfig> getBranches() {
        return branches;
    }

    public void setGatewayType(GatewayType gatewayType) {
        this.gatewayType = gatewayType;
    }

    @Override
    public void accept(BpmNodeVisitor visitor) {
        if (visitor.visit(this)) {
            for (BranchNodeConfig branch : branches) {
                branch.accept(visitor);
            }
            visitor.endVisit(this);
        }
    }

    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.GATEWAY;
    }

}
