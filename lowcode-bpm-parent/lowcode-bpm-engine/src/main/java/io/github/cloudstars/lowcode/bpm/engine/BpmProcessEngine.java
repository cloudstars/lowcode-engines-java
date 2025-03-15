package io.github.cloudstars.lowcode.bpm.engine;

import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;

import java.util.Map;

/**
 * 流程引擎
 *
 * @author clouds
 */
public interface BpmProcessEngine {

    /**
     * 部署流程
     *
     * @param config
     * @return
     */
    String deploy(ProcessConfig config);

    /**
     * 启动流程
     *
     * @param processKey 流程编号
     * @param dataMap 流程数据
     * @return 流程实例编号
     */
    String start(String processKey, Map<String, Object> dataMap);

    /**
     * 提交任务
     *
     * @param taskId 任务ID
     * @param dataMap 任务数据
     */
    void completeTask(String taskId, Map<String, Object> dataMap);

}
