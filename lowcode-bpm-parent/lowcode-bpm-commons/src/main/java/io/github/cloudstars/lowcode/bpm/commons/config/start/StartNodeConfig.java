package io.github.cloudstars.lowcode.bpm.commons.config.start;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 开始节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.START")
public class StartNodeConfig extends AbstractNodeConfig {

    public StartNodeConfig() {
    }

    public StartNodeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.START;
    }

    @Override
    public void accept(BpmNodeVisitor visitor) {
        if (visitor.visit(this)) {
            visitor.endVisit(this);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        //
        return configJson;
    }

}
