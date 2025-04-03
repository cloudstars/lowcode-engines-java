package io.github.cloudstars.lowcode.bpm.commons.config.gateway;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.condition.ConditionConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 网关分支节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.GATEWAY.BRANCH")
public class GatewayBranchNodeConfig extends BranchNodeConfig {

    /**
     * 分支条件
     */
    private ConditionConfig condition;

    public GatewayBranchNodeConfig() {
    }

    public GatewayBranchNodeConfig(JsonObject configJson) {
        super(configJson);

        JsonObject conditionJson = (JsonObject) configJson.get("condition");
        this.condition = new ConditionConfig(conditionJson);
    }

    public ConditionConfig getCondition() {
        return condition;
    }

    public void setCondition(ConditionConfig condition) {
        this.condition = condition;
    }

    @Override
    public void accept(BpmNodeVisitor visitor) {
        super.accept(visitor);
    }

    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.GATEWAY;
    }

}
