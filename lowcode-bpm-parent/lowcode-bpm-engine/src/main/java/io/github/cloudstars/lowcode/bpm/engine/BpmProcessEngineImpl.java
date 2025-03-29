package io.github.cloudstars.lowcode.bpm.engine;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmProvider;

import java.util.Map;

/**
 * 流程引擎实现
 *
 * @author clouds 
 */
public class BpmProcessEngineImpl implements BpmProcessEngine {

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
        return null;
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> dataMap) {

    }
}
