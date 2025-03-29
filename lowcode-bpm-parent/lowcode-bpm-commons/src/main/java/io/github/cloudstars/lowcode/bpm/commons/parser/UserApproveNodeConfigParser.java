package io.github.cloudstars.lowcode.bpm.commons.parser;

import io.github.cloudstars.lowcode.bpm.commons.config.user.UserApproveNodeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 用户审批节点配置解析器
 *
 * @author clouds
 */
public class UserApproveNodeConfigParser extends AbstractNodeConfigParser<UserApproveNodeConfig> {

    @Override
    public UserApproveNodeConfig fromJson(JsonObject nodeConfigJson) {
        UserApproveNodeConfig nodeConfig = new UserApproveNodeConfig();
        this.initCommonNode(nodeConfig, nodeConfigJson);
        return nodeConfig;
    }

}
