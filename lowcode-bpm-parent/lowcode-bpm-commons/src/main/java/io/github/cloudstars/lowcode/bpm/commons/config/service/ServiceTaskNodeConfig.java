package io.github.cloudstars.lowcode.bpm.commons.config.service;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;

/**
 * 抽象的程序节点配置
 *
 * @author clouds
 */
public abstract class ServiceTaskNodeConfig extends AbstractNodeConfig {

    @Override
    protected NodeTypeEnum getType() {
        return NodeTypeEnum.SERVICE;
    }

}
