package io.github.cloudstars.lowcode.bpm.editor.config.end;

import io.github.cloudstars.lowcode.bpm.editor.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;
import io.github.cloudstars.lowcode.bpm.editor.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 结束节点定义
 *
 * @author clouds
 */
public class EndNodeConfig extends AbstractNodeConfig {

    @Override
    public NodeType getType() {
        return NodeType.END;
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
