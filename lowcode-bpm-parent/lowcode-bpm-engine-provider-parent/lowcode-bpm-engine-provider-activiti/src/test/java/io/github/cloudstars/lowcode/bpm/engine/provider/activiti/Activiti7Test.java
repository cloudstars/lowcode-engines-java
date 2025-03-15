package io.github.cloudstars.lowcode.bpm.engine.provider.activiti;

import io.github.cloudstars.lowcode.ActivitiBpmEngineProviderTestApplication;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiBpmEngineProviderTestApplication.class)
public class Activiti7Test {

    private static final Logger logger = LoggerFactory.getLogger(Activiti7Test.class);

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Test
    public void testDeployment() {
        Deployment deployment = repositoryService.createDeployment()
                .name("测试流程文件部署")
                .key("leave")
                .addClasspathResource("processes/leave.bpmn")
                //.addClasspathResource("processes/leave.jpg")
                .deploy();

        logger.info("部署的流程id = {}", deployment.getId());
        logger.info("部署的流程名称 = {}", deployment.getName());
    }

    @Test
    public void testStartProcess() {
        String processKey = "leave";
        logger.info("=== 启动请假流程 ===");
        Map<String, Object> map = new HashMap<>();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, map);
        logger.info("启动流程实例成功 = {}", processInstance);
        logger.info("流程实例id = {}", processInstance.getId());
        logger.info("流程定义id = {}", processInstance.getProcessDefinitionId());
    }

    @Test
    public void testGetTaskByAssignee() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee("clouds")
                .list();
        Map<String, Object> map = new HashMap<>();
        map.put("leaveReason", "请年假一天");
        map.put("leaveDays", 1);
        for (Task task : tasks) {
            logger.info("任务id = {}", task.getId());
            taskService.complete(task.getId(), map);
        }
    }
}
