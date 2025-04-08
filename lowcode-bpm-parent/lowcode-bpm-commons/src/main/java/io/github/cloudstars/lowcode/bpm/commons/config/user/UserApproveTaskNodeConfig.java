package io.github.cloudstars.lowcode.bpm.commons.config.user;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 用户审批节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.USER.APPROVE")
public class UserApproveTaskNodeConfig extends AbstractUserTaskNodeConfig {

    public UserApproveTaskNodeConfig() {
    }

    public UserApproveTaskNodeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.USER_APPROVE;
    }

    @Override
    public void accept(BpmNodeVisitor visitor) {
        if (visitor.visit(this)) {
            visitor.endVisit(this);
        }
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
