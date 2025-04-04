package io.github.cloudstars.lowcode.bpm.vendor;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;

/**
 * Bpm部署提供方接口
 *
 * @author clouds
 */
public interface BpmDeployProvider {

    /**
     * 部署一个流程
     *
     * @param config 流程定义
     * @return 流程部署ID
     */
    String deploy(ProcessConfig config);

}
