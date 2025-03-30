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
@NodeConfigClass(name = "DEFAULT.USER.APPROVE")
public class UserApproveNodeConfig extends AbstractUserNodeConfig {

    public UserApproveNodeConfig() {
    }

    public UserApproveNodeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    protected NodeTypeEnum getType() {
        return NodeTypeEnum.USER_APPROVE;
    }

    @Override
    protected void accept0(BpmNodeVisitor visitor) {
        if (visitor.visit(this)) {
            visitor.endVisit(this);
        }
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
