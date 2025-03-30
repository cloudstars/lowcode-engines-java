package io.github.cloudstars.lowcode.bpm.provider.activiti;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.start.StartNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.AbstractUserNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserApproveNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserWriteNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;

/**
 * Activiti模型构建的BPM节点访问器
 *
 * @author clouds
 */
public class ProcessBuildBpmNodeVisitor implements BpmNodeVisitor {

    /**
     * Activiti模型
     */
    private Process process;

    /**
     * 前一个访问的节点配置
     */
    private AbstractNodeConfig prevNodeConfig;


    public ProcessBuildBpmNodeVisitor(Process process) {
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

    @Override
    public boolean visit(StartNodeConfig nodeConfig) {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(nodeConfig.getKey());
        startEvent.setName(nodeConfig.getName());
        this.process.addFlowElement(startEvent);
        this.prevNodeConfig = nodeConfig;

        return false;
    }

    @Override
    public boolean visit(UserApproveNodeConfig nodeConfig) {
        // 创建用户审批任务
        UserTask userTask = this.createUserTask(nodeConfig);
        process.addFlowElement(userTask);

        // 创建前一个节点到当前用户审批节点的边
        SequenceFlow sequenceFlow = this.createSequenceFlowFromPrev(nodeConfig);
        process.addFlowElement(sequenceFlow);
        this.prevNodeConfig = nodeConfig;

        return false;
    }

    @Override
    public boolean visit(UserWriteNodeConfig nodeConfig) {
        // 创建用户填写任务
        UserTask userTask = this.createUserTask(nodeConfig);
        process.addFlowElement(userTask);

        // 创建前一个节点到当前用户填写节点的边
        SequenceFlow sequenceFlow = this.createSequenceFlowFromPrev(nodeConfig);
        process.addFlowElement(sequenceFlow);
        this.prevNodeConfig = nodeConfig;

        return false;
    }

    @Override
    public boolean visit(EndNodeConfig nodeConfig) {
        // 创建结束事件
        EndEvent endEvent = new EndEvent();
        String nodeKey = nodeConfig.getKey();
        endEvent.setId(nodeKey);
        endEvent.setName(nodeConfig.getName());
        this.process.addFlowElement(endEvent);
        this.prevNodeConfig = nodeConfig;

        // 创建前一个节点到结束事件的边
        String prevNodeKey = this.prevNodeConfig.getKey();
        SequenceFlow sequenceFlow = this.createSequenceFlow(prevNodeKey, nodeKey);
        process.addFlowElement(sequenceFlow);

        return false;
    }

    /**
     * 创建用户任务
     *
     * @param nodeConfig 用户节点配置
     * @return 用户任务
     */
    protected UserTask createUserTask(AbstractUserNodeConfig nodeConfig) {
        UserTask userTask = new UserTask();
        String nodeKey = nodeConfig.getKey();
        userTask.setId(nodeKey);
        userTask.setName(nodeConfig.getName());
        userTask.setAssignee("XXX");
        return userTask;
    }

    /**
     * 创建前一个节点到指定节点的边
     *
     * @param nodeConfig
     * @return
     */
    protected SequenceFlow createSequenceFlowFromPrev(NodeConfig nodeConfig) {
        String nodeKey = nodeConfig.getKey();
        String prevNodeKey = prevNodeConfig.getKey();
        SequenceFlow sequenceFlow = new SequenceFlow();
        sequenceFlow.setSourceRef(prevNodeKey);
        sequenceFlow.setTargetRef(nodeKey);
        return sequenceFlow;
    }

    /**
     * 创建两个节点的边
     *
     * @param from
     * @param to
     * @return
     */
    protected SequenceFlow createSequenceFlow(String from, String to) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        return flow;
    }

}
