package io.github.cloudstars.lowcode.bpm.engine;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 流程引擎实现
 *
 * @author clouds 
 */
public class BpmProcessEngineImpl implements BpmProcessEngine {

    private final static Logger logger = LoggerFactory.getLogger(BpmProcessEngineImpl.class);

    /**
     * 流程引擎提供方
     */
    private BpmProvider bpmProvider;

    public BpmProcessEngineImpl(BpmProvider bpmProvider) {
        this.bpmProvider = bpmProvider;
    }

    @Override
    public String deploy(ProcessConfig config) {
        String deployId = this.bpmProvider.deploy(config);
        return deployId;
    }

    @Override
    public String start(String processKey, Map<String, Object> dataMap) {
        String processInstId = this.bpmProvider.start(processKey, dataMap);
        logger.info("流程启动调用成功，返回流程实例ID：{}", processInstId);
        return processInstId;
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> dataMap) {

    }
}
