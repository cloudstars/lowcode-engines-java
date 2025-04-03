package io.github.cloudstars.lowcode.bpm.provider.activiti;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmProvider;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 基于Activiti的BPM引擎实现类
 *
 * @author clouds
 */
public class ActivitiBpmProviderImpl implements BpmProvider {

    private final static Logger logger = LoggerFactory.getLogger(ActivitiBpmProviderImpl.class);

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Override
    public String deploy(ProcessConfig config) {
        //创建bpmn模型
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        process.setId(config.getKey());
        process.setName(config.getName());

        ProcessBuildBpmNodeVisitor visitor = new ProcessBuildBpmNodeVisitor(process);
        config.getMainBranch().accept(visitor);
        model.addProcess(process);

        // 部署流程
        String resourceId = String.format("%s.bpmn20.xml", config.getKey());
        Deployment deploy = this.repositoryService.createDeployment().addBpmnModel(resourceId, model).deploy();
        String deployId = deploy.getId();
        ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        return processDefinition.getId();
    }

    @Override
    public String start(String processKey, Map<String, Object> dataMap) {
        ProcessInstance processInst = this.runtimeService.startProcessInstanceByKey(processKey, dataMap);
        String processInstId = processInst.getProcessInstanceId();
        logger.info("Activiti流程启动成功，实例ID：{}", processInstId);
        return processInstId;
    }

}
