package io.github.cloudstars.lowcode.bpm.engine.test.user;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessService;
import io.github.cloudstars.lowcode.bpm.engine.service.task.BpmTaskService;
import io.github.cloudstars.lowcode.bpm.engine.test.ProcessClassPaths;
import io.github.cloudstars.lowcode.bpm.engine.test.ProcessConfigLoader;
import io.github.cloudstars.lowcode.bpm.vendor.query.TaskInfo;
import io.github.cloudstars.lowcode.bpm.vendor.query.TaskInfoQuery;
import org.junit.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽象的流程服务测试类，定义了测试的规范（输入/输出）
 *
 * @author clouds
 */
public abstract class AbstractProcessServiceTest {

    @Resource
    private BpmDeployService deployService;

    @Resource
    private BpmProcessService processService;

    @Resource
    private BpmTaskService taskService;

    /**
     * 测试启动流程定义（简单不含分支的流程）
     */
    protected void testStartSimple1() {
        ProcessConfig config = ProcessConfigLoader.loadFromClassPath(ProcessClassPaths.USER_SIMPLE1);
        String processKey = config.getKey();

        // 先部署再启动流程
        Date current = new Date();
        this.deployService.deploy(config);
        this.processService.start(processKey, new HashMap<>());

        // 查询晚于current并且处理人是clouds1、clouds2的任务
        TaskInfoQuery query = this.taskService.createQuery();
        List<TaskInfo> clouds1TaskInfos = this.fetchTodoList(query, "clouds1", current);
        List<TaskInfo> clouds2TaskInfos = this.fetchTodoList(query, "clouds2", current);
        Assert.assertEquals(1, clouds1TaskInfos.size());
        Assert.assertEquals(0, clouds2TaskInfos.size());

        // 提交clouds1任务，clouds1的任务结束，clouds2会产生一条任务
        current = new Date();
        String task1Id = clouds1TaskInfos.get(0).getId();
        this.taskService.submit(task1Id, new HashMap<>());

        // 查询晚于current并且处理人是clouds1、clouds2的任务
        clouds1TaskInfos = this.fetchTodoList(query, "clouds1", current);
        clouds2TaskInfos = this.fetchTodoList(query, "clouds2", current);
        Assert.assertEquals(0, clouds1TaskInfos.size());
        Assert.assertEquals(1, clouds2TaskInfos.size());

        // 提交clouds2任务，clouds1、clouds2的任务都结束了
        current = new Date();
        String task2Id = clouds2TaskInfos.get(0).getId();
        this.taskService.submit(task2Id, new HashMap<>());

        // 查询晚于current并且处理人是clouds1、clouds2的任务
        clouds1TaskInfos = this.fetchTodoList(query, "clouds1", current);
        clouds2TaskInfos = this.fetchTodoList(query, "clouds2", current);
        Assert.assertEquals(0, clouds1TaskInfos.size());
        Assert.assertEquals(0, clouds2TaskInfos.size());
    }

    /**
     * 断言处理人的待办任务数据
     *
     * @param query 任务查询
     * @param assignee 查询的处理人
     * @param current 查询的时间起点
     */
    public List<TaskInfo> fetchTodoList(TaskInfoQuery query, String assignee, Date current) {
        List<TaskInfo> taskInfos1 = query.taskAssignee(assignee).list();
        return taskInfos1.stream()
                .filter((t) -> assignee.equals(t.getAssignee()) && t.getCreateTime().after(current))
                .collect(Collectors.toList());
    }

}
