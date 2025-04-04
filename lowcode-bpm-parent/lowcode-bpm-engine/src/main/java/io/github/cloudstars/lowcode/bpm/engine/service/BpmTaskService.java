package io.github.cloudstars.lowcode.bpm.engine.service;

import java.util.Map;

/**
 * 流程任务服务
 *
 * @author clouds
 */
public interface BpmTaskService {

    /**
     * 审批通过任务
     * 
     * @param taskId
     * @param dataMap
     */
    void pass(String taskId, Map<String, Object> dataMap);

    /**
     * 审批驳回任务（驳回后流程结束）
     *
     * @param taskId 任务ID
     * @param dataMap 任务数据
     */
    void reject(String taskId, Map<String, Object> dataMap);

    /**
     * 任务提交（用于填写节点，如果审批节点调用了上此接口等同于pass）
     *
     * @param taskId
     * @param dataMap
     */
    void submit(String taskId, Map<String, Object> dataMap);

    /**
     * 任务打回到某个节点
     *
     * @param taskId
     * @param targetNodeKey
     * @param dataMap
     */
    void returnBackTo(String taskId, String targetNodeKey, Map<String, Object> dataMap);

    /**
     * 任务打回至发起节点
     *
     * @param taskId
     */
    void returnBackToStart(String taskId, Map<String, Object> dataMap);

}
