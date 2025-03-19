package io.github.cloudstars.lowcode.bpm.editor.config.user;

import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;
import io.github.cloudstars.lowcode.bpm.editor.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 用户审批节点配置
 *
 * @author clouds
 */
public class UserApproveNodeConfig extends AbstractUserNodeConfig {

    @Override
    protected NodeType getType() {
        return NodeType.USER_APPROVE;
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
