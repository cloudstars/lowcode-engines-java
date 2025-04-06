package io.github.cloudstars.lowcode.bpm.vendor.activiti.query;

import io.github.cloudstars.lowcode.bpm.vendor.query.QueryProperty;
import io.github.cloudstars.lowcode.bpm.vendor.query.TaskInfo;
import io.github.cloudstars.lowcode.bpm.vendor.query.TaskInfoQuery;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 任务信息查询实现
 *
 * @author clouds
 */
public class TaskInfoQueryImpl implements TaskInfoQuery<TaskInfoQuery<?, ?>, TaskInfo> {

    /**
     * Activiti的任务服务
     */
    private TaskService taskService;

    private TaskQuery taskQuery;

    public TaskInfoQueryImpl(TaskService taskService) {
        this.taskService = taskService;
        this.taskQuery = taskService.createTaskQuery();
    }

    @Override
    public TaskInfoQuery asc() {
        return null;
    }

    @Override
    public TaskInfoQuery desc() {
        return null;
    }

    @Override
    public TaskInfoQuery orderBy(QueryProperty var1) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public TaskInfo singleResult() {
        return null;
    }

    @Override
    public List<TaskInfo> list() {
        List<Task> tasks = this.taskQuery.list();
        return tasks.stream().map((task -> toTaskInfo(task))).collect(Collectors.toList());
    }

    private TaskInfo toTaskInfo(Task task) {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setId(task.getId());
        taskInfo.setName(task.getName());
        taskInfo.setDescription(task.getDescription());
        taskInfo.setOwner(task.getOwner());
        taskInfo.setAssignee(task.getAssignee());
        taskInfo.setCreateTime(task.getCreateTime());
        taskInfo.setFormKey(task.getFormKey());
        taskInfo.setBusinessKey(task.getBusinessKey());
        return taskInfo;
    }

    @Override
    public List<TaskInfo> listPage(int var1, int var2) {
        return null;
    }

    @Override
    public TaskInfoQuery taskId(String var1) {
        return null;
    }

    @Override
    public TaskInfoQuery taskName(String var1) {
        return null;
    }

    @Override
    public TaskInfoQuery taskNameLike(String var1) {
        return null;
    }

    @Override
    public TaskInfoQuery taskDescription(String var1) {
        return null;
    }

    @Override
    public TaskInfoQuery taskDescriptionLike(String var1) {
        return null;
    }

    @Override
    public TaskInfoQuery taskAssignee(String assignee) {
        this.taskQuery.taskAssignee(assignee);
        return this;
    }

    @Override
    public TaskInfoQuery taskOwner(String var1) {
        return null;
    }

    @Override
    public TaskInfoQuery taskCreatedOn(Date var1) {
        return null;
    }

    @Override
    public TaskInfoQuery taskCreatedBefore(Date var1) {
        return null;
    }

    @Override
    public TaskInfoQuery taskCreatedAfter(Date var1) {
        return null;
    }
}
