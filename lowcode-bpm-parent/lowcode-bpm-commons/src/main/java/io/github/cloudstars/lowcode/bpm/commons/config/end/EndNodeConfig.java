package io.github.cloudstars.lowcode.bpm.commons.config.end;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 结束节点配置
 *
 * @author clouds
 */
@NodeConfigClass(name = "DEFAULT.END")
public class EndNodeConfig extends AbstractNodeConfig {

    public EndNodeConfig() {
    }

    public EndNodeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public NodeTypeEnum getType() {
        return NodeTypeEnum.END;
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
