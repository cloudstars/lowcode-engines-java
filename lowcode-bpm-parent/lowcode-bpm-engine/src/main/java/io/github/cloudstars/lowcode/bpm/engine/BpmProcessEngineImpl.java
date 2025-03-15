package io.github.cloudstars.lowcode.bpm.engine;

import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmEngineProvider;

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
    private BpmEngineProvider bpmEngineProvider;

    public BpmProcessEngineImpl(BpmEngineProvider bpmEngineProvider) {
        this.bpmEngineProvider = bpmEngineProvider;
    }

    @Override
    public String deploy(ProcessConfig config) {
        String deployId = this.bpmEngineProvider.deploy(config);
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
