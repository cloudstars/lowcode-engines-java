package io.github.cloudstars.lowcode.bpm.engine.service;

import java.util.Map;

/**
 * 流程引擎
 *
 * @author clouds
 */
public interface BpmProcessService {

    /**
     * 启动流程
     *
     * @param processKey 流程编号
     * @param dataMap 流程数据
     * @return 流程实例编号
     */
    String start(String processKey, Map<String, Object> dataMap);

    /**
     * 挂起流程
     *
     * @param processInstId
     */
    void suspend(String processInstId);

    /**
     * 恢复流程
     *
     * @param processInstId
     */
    void resume(String processInstId);


    /**
     * 中止流程实例
     *
     * @param processInstId
     */
    void terminate(String processInstId);

    /**
     * 删除流程实例
     *
     * @param processInstId
     */
    void delete(String processInstId);

}
