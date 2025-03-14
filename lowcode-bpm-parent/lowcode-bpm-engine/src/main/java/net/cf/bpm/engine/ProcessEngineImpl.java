package net.cf.bpm.engine;

import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.provider.EngineProvider;

import java.util.Map;

/**
 * 流程引擎实现
 *
 * @author clouds 
 */
public class ProcessEngineImpl implements ProcessEngine {

    /**
     * 流程引擎提供方
     */
    private EngineProvider engineProvider;

    public ProcessEngineImpl(EngineProvider engineProvider) {
        this.engineProvider = engineProvider;
    }

    @Override
    public String deploy(ProcessConfig config) {
        String deployId = this.engineProvider.deploy(config);
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
