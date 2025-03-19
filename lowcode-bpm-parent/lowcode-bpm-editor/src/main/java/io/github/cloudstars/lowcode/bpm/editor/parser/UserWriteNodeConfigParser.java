package io.github.cloudstars.lowcode.bpm.editor.parser;

import io.github.cloudstars.lowcode.bpm.editor.config.user.UserWriteNodeConfig;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 用户填写节点配置解析器
 *
 * @author clouds
 */
public class UserWriteNodeConfigParser extends AbstractNodeConfigParser<UserWriteNodeConfig> {

    @Override
    public UserWriteNodeConfig fromJson(JsonObject nodeConfigJson) {
        UserWriteNodeConfig nodeConfig = new UserWriteNodeConfig();
        this.initCommonNode(nodeConfig, nodeConfigJson);
        return nodeConfig;
    }

}
