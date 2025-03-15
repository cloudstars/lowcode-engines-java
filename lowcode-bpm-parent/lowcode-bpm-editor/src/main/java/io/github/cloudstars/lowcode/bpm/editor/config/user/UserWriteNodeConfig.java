package io.github.cloudstars.lowcode.bpm.editor.config.user;

import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;

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

}
