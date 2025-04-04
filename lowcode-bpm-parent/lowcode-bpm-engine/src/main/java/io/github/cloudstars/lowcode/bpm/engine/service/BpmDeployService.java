package io.github.cloudstars.lowcode.bpm.engine.service;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;

/**
 * 流程部署引擎
 *
 * @author clouds
 */
public interface BpmDeployService {

    /**
     * 部署流程
     *
     * @param config
     * @return
     */
    String deploy(ProcessConfig config);

    /**
     * 卸载流程
     * 
     * @param deployId
     */
    void undeploy(String deployId);
}
