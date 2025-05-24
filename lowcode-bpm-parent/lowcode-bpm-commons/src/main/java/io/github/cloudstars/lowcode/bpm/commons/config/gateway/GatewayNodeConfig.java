package io.github.cloudstars.lowcode.bpm.commons.config.gateway;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 网关节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.GATEWAY.EXCLUSIVE")
public class GatewayNodeConfig extends AbstractGatewayNodeConfig {

    public GatewayNodeConfig() {
    }

    public GatewayNodeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public void accept(BpmNodeVisitor visitor) {
        if (visitor.visit(this)) {
            for (BranchNodeConfig branch : branches) {
                branch.accept(visitor);
            }
            visitor.endVisit(this);
        }
    }

    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.GATEWAY;
    }

}
