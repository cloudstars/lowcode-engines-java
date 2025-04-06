package io.github.cloudstars.lowcode.bpm.engine.service.task;

import io.github.cloudstars.lowcode.bpm.vendor.BpmTaskProvider;
import io.github.cloudstars.lowcode.bpm.vendor.query.TaskInfo;
import io.github.cloudstars.lowcode.bpm.vendor.query.TaskInfoQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 流程任务服务实现
 *
 * @author clouds 
 */
public class BpmTaskServiceImpl implements BpmTaskService {

    private final static Logger logger = LoggerFactory.getLogger(BpmTaskServiceImpl.class);

    /**
     * 流程引擎任务提供方
     */
    private BpmTaskProvider taskProvider;

    public BpmTaskServiceImpl(BpmTaskProvider taskProvider) {
        this.taskProvider = taskProvider;
    }

    @Override
    public void pass(String taskId, Map<String, Object> dataMap) {

    }

    @Override
    public void reject(String taskId, Map<String, Object> dataMap) {

    }

    @Override
    public void submit(String taskId, Map<String, Object> dataMap) {
        this.taskProvider.completeTask(taskId, dataMap);
    }

    @Override
    public void returnBackTo(String taskId, String targetNodeKey, Map<String, Object> dataMap) {

    }

    @Override
    public void returnBackToStart(String taskId, Map<String, Object> dataMap) {

    }

    @Override
    public TaskInfoQuery<?, TaskInfo> createQuery() {
        return this.taskProvider.createTaskQuery();
    }

}
