package io.github.cloudstars.lowcode.bpm.engine.provider.activiti;

import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmEngineProvider;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.activiti.engine.RepositoryService;

import javax.annotation.Resource;

/**
 * 基于Activiti的BPM引擎实现类
 *
 * @author clouds
 */
public class ActivitiBpmEngineProviderImpl implements BpmEngineProvider {


    @Resource
    private RepositoryService repositoryService;

    @Override
    public String deploy(ProcessConfig config) {

        //创建bpmn模型
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        process.setId(config.getKey());


        // 创建bpmn元素
        process.addFlowElement(createStartEvent());
        process.addFlowElement(createUserTask("task1", "First task", "fred"));
        process.addFlowElement(createUserTask("task2", "Second task", "john"));
        process.addFlowElement(createEndEvent());

        process.addFlowElement(createSequenceFlow("start", "task1"));
        process.addFlowElement(createSequenceFlow("task1", "task2"));
        process.addFlowElement(createSequenceFlow("task2", "end"));

        model.addProcess(process);

        // 部署流程
        /*Deployment deploy = this.repositoryService.createDeployment().addBpmnModel(String.format("%s.bpmn20.xml", UUID.randomUUID()), model).deploy();
        String deployId = deploy.getId();
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        return processDefinition.getId();*/
        return "xxx";
    }

    // 创建task
    protected UserTask createUserTask(String id, String name, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee(assignee);
        return userTask;
    }


    //创建箭头
    protected SequenceFlow createSequenceFlow(String from, String to) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        return flow;
    }


    protected StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start");
        return startEvent;
    }


    protected EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("end");
        return endEvent;
    }


}
