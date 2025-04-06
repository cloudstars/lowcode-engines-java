package io.github.cloudstars.lowcode.bpm.vendor.activiti;

import io.github.cloudstars.lowcode.bpm.vendor.BpmTaskProvider;
import io.github.cloudstars.lowcode.bpm.vendor.activiti.query.TaskInfoQueryImpl;
import io.github.cloudstars.lowcode.bpm.vendor.query.TaskInfoQuery;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 基于Activiti的BPM任务提供实现
 *
 * @author clouds
 */
public class ActivitiBpmTaskProviderImpl implements BpmTaskProvider {

    private final static Logger logger = LoggerFactory.getLogger(ActivitiBpmTaskProviderImpl.class);

    @Resource
    private TaskService taskService;

    @Override
    public TaskInfoQuery createTaskQuery() {
        return new TaskInfoQueryImpl(this.taskService);
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> varMap) {
        this.taskService.complete(taskId, varMap);
    }
}
