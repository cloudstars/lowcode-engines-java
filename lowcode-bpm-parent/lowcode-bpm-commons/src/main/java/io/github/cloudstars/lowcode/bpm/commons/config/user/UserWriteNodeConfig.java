package io.github.cloudstars.lowcode.bpm.commons.config.user;

import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 用户填写节点配置
 *
 * @author clouds
 */
@NodeConfigClass(name = "DEFAULT.USER.WRITE")
public class UserWriteNodeConfig extends AbstractUserNodeConfig {

    public UserWriteNodeConfig() {
    }

    public UserWriteNodeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    protected NodeTypeEnum getType() {
        return NodeTypeEnum.USER_WRITE;
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
