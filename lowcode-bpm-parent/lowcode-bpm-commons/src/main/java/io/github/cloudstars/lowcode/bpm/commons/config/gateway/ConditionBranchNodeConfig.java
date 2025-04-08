package io.github.cloudstars.lowcode.bpm.commons.config.gateway;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.condition.ConditionConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 条件分支节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.CONDITION.BRANCH")
public class ConditionBranchNodeConfig extends BranchNodeConfig {

    /**
     * 分支条件
     */
    private ConditionConfig condition;

    public ConditionBranchNodeConfig() {
    }

    public ConditionBranchNodeConfig(JsonObject configJson) {
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
        if (visitor.visit(this)) {
            super.accept(visitor);
            visitor.endVisit(this);
        }
    }

    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.GATEWAY;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put("condition", this.condition);

        return configJson;
    }

}
