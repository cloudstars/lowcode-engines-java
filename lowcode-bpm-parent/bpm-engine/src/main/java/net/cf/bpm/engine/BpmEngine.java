package net.cf.bpm.engine;

import java.util.Map;

/**
 * BPM引擎接口
 *
 * @author clouds
 */
public interface BpmEngine {

    /**
     * 启动流程
     *
     * @param processKey
     * @param dataMap
     * @return
     */
    String startProcess(String processKey, Map<String, Object> dataMap);

    /**
     * 提交任务
     *
     * @param taskId
     * @param dataMap
     * @return
     */
    void completeTask(String taskId, Map<String, Object> dataMap);
}
