package io.github.cloudstars.lowcode.bpm.editor.config.start;

import io.github.cloudstars.lowcode.bpm.editor.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;
import io.github.cloudstars.lowcode.bpm.editor.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 开始节点定义
 *
 * @author clouds
 */
public class StartNodeConfig extends AbstractNodeConfig {

    @Override
    public NodeType getType() {
        return NodeType.START;
    }

    @Override
    protected void accept0(BpmNodeVisitor visitor) {
        if (visitor.visit(this)) {
            visitor.endVisit(this);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();
        //
        return jsonObject;
    }

}
