package net.cf.bpm.engine;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BpmEngineImpl implements BpmEngine {

    @Override
    public String startProcess(String processKey, Map<String, Object> dataMap) {
        return null;
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> dataMap) {
    }
}
