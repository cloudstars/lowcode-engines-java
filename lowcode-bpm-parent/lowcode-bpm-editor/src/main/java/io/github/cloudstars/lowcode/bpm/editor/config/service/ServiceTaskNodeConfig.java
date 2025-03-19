package io.github.cloudstars.lowcode.bpm.editor.config.service;

import io.github.cloudstars.lowcode.bpm.editor.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;

/**
 * 抽象的程序节点配置
 *
 * @author clouds
 */
public abstract class ServiceTaskNodeConfig extends AbstractNodeConfig {

    @Override
    protected NodeType getType() {
        return NodeType.SERVICE;
    }

}
