package io.github.cloudstars.lowcode.bpm.commons.config.gateway;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 抽象的网关节点配置
 *
 * @author clouds
 */
public abstract class AbstractGatewayNodeConfig extends AbstractNodeConfig {

    // 网关分支配置项名称
    protected static final String ATTR_BRANCHES = "branches";

    /**
     * 网关类型
     */
    protected GatewayType gatewayType;

    /**
     * 网关下的分支列表
     */
    protected List<ConditionBranchNodeConfig> branches;

    public AbstractGatewayNodeConfig() {
    }

    public AbstractGatewayNodeConfig(JsonObject configJson) {
        super(configJson);

        Object gatewayTypeValue = configJson.get("gatewayType");
        if (gatewayTypeValue != null) {
            this.gatewayType = GatewayType.valueOf((String) gatewayTypeValue);
        } else {
            this.gatewayType = GatewayType.OR;
        }

        this.branches = ConfigUtils.getRequiredList(configJson, "branches", (v) -> new ConditionBranchNodeConfig(v));

        // 建立网关与分支的前后关系，网关分支节点的前后节点都是网关节点
        for (ConditionBranchNodeConfig branch : this.branches) {
            branch.setPrevNode(this);
            branch.setNextNode(this);
        }

    }

    public GatewayType getGatewayType() {
        return gatewayType;
    }

    public List<ConditionBranchNodeConfig> getBranches() {
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

}
