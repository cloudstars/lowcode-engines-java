package io.github.cloudstars.lowcode.bpm.editor.config.user;

import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;
import io.github.cloudstars.lowcode.bpm.editor.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 用户填写节点配置
 *
 * @author clouds
 */
public class UserWriteNodeConfig extends AbstractUserNodeConfig {

    @Override
    protected NodeType getType() {
        return NodeType.USER_WRITE;
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
