package io.github.cloudstars.lowcode.bpm.editor.config.user;

import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;

/**
 * 用户审批节点配置
 *
 * @author clouds
 */
public class UserApproveNodeConfig extends AbstractUserNodeConfig {

    @Override
    protected NodeType getType() {
        return NodeType.USER_APPROVE;
    }

}
