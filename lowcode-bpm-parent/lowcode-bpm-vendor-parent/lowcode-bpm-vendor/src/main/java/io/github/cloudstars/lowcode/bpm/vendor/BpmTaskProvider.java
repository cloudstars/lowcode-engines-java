package io.github.cloudstars.lowcode.bpm.vendor;

import io.github.cloudstars.lowcode.bpm.vendor.query.TaskInfoQuery;

import java.util.Map;

/**
 * Bpm任务提供方接口
 *
 * @author clouds
 */
public interface BpmTaskProvider {

    /**
     * 创建一个任务信息查询
     *
     * @return
     */
    default TaskInfoQuery createTaskQuery() {
        return null;
    }

    /**
     * 提交任务
     *
     * @param taskId
     * @param varMap
     */
    default void completeTask(String taskId, Map<String, Object> varMap) {
    }

}
