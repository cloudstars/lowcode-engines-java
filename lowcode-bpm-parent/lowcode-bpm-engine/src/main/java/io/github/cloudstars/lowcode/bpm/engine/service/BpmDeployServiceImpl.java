package io.github.cloudstars.lowcode.bpm.engine.service;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.vendor.BpmDeployProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流程部署服务实现
 *
 * @author clouds 
 */
public class BpmDeployServiceImpl implements BpmDeployService {

    private final static Logger logger = LoggerFactory.getLogger(BpmDeployServiceImpl.class);

    /**
     * 流程引擎提供方
     */
    private BpmDeployProvider bpmDeployProvider;

    public BpmDeployServiceImpl(BpmDeployProvider bpmDeployProvider) {
        this.bpmDeployProvider = bpmDeployProvider;
    }

    @Override
    public String deploy(ProcessConfig config) {
        String deployId = this.bpmDeployProvider.deploy(config);
        return deployId;
    }

    @Override
    public void undeploy(String deployId) {

    }

}
