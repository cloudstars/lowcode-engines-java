package io.github.cloudstars.lowcode.bpm.vendor.activiti;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.ConditionBranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.GatewayNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.loop.LoopBranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.service.ServiceTaskNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.start.StartNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.AbstractUserTaskNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserApproveTaskNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserWriteTaskNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.bpm.vendor.activiti.service.ActivitiBpmServiceTask;
import io.github.cloudstars.lowcode.bpm.vendor.activiti.service.LoopVarServiceTask;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public ProcessBuildBpmNodeVisitor(Process process) {
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

    @Override
    public void endVisit(StartNodeConfig nodeConfig) {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(nodeConfig.getKey());
        startEvent.setName(nodeConfig.getName());
        this.process.addFlowElement(startEvent);
    }

    @Override
    public void endVisit(UserApproveTaskNodeConfig nodeConfig) {
        // 创建用户审批任务
        UserTask userTask = this.createUserTask(nodeConfig);
        process.addFlowElement(userTask);

        // 创建前一个节点到当前用户审批节点的边
        this.createSequenceFlowFromPrev(nodeConfig);
    }

    @Override
    public void endVisit(UserWriteTaskNodeConfig nodeConfig) {
        // 创建用户填写任务
        UserTask userTask = this.createUserTask(nodeConfig);
        process.addFlowElement(userTask);

        // 创建前一个节点到当前用户填写节点的边
        this.createSequenceFlowFromPrev(nodeConfig);
    }

    @Override
    public void endVisit(ServiceTaskNodeConfig nodeConfig) {
        ServiceTask serviceTask = this.createServiceTask(nodeConfig);
        process.addFlowElement(serviceTask);

        // 创建前一个节点到当前程序节点的边
        this.createSequenceFlowFromPrev(nodeConfig);
    }

    @Override
    public void endVisit(EndNodeConfig nodeConfig) {
        String nodeKey = nodeConfig.getKey();

        // 创建结束事件
        EndEvent endEvent = new EndEvent();
        endEvent.setId(nodeKey);
        endEvent.setName(nodeConfig.getName());
        this.process.addFlowElement(endEvent);

        // 创建前一个节点到当前用户审批节点的边
        this.createSequenceFlowFromPrev(nodeConfig);
    }

    @Override
    public boolean visit(LoopBranchNodeConfig nodeConfig) {
        // 在循环分支前添加一个循环变量定义的ServiceTask
        ServiceTask varServiceTask = new ServiceTask();
        String varServiceTaskFlowId = LoopVarServiceTask.getVarTaskFlowId(nodeConfig);
        varServiceTask.setId(varServiceTaskFlowId);
        varServiceTask.setName(nodeConfig.getName() + "-变量");
        String varInfos = nodeConfig.getItems() + LoopVarServiceTask.VAR_SEPARATOR + nodeConfig.getItem();
        varServiceTask.setDocumentation(varInfos);
        varServiceTask.setImplementationType("class");
        varServiceTask.setImplementation(LoopVarServiceTask.class.getName());
        this.process.addFlowElement(varServiceTask);

        //从网关前一个前点拉一条边到变量节点
        this.createSequenceFlow(this.getPrevNodeFlowId(nodeConfig), varServiceTaskFlowId);

        // 生成开始网关（1入边2出边）
        ExclusiveGateway startGateway = new ExclusiveGateway();
        String startGatewayFlowId = this.getStartGatewayFlowId(nodeConfig);
        String endGatewayFlowId = this.getEndGatewayFlowId(nodeConfig);
        startGateway.setId(startGatewayFlowId);
        startGateway.setName(nodeConfig.getName() + "-start");

        // 建立变量定义节点的网关开始节点的边
        SequenceFlow incomingFlow0 = this.createSequenceFlow(varServiceTaskFlowId, startGatewayFlowId);
        startGateway.setIncomingFlows(Arrays.asList(incomingFlow0));
        // 建立开始网关到循环分支第一个节点的出边
        List<AbstractNodeConfig> branchNodes = nodeConfig.getNodes();
        SequenceFlow outgoingFlow0 = null;
        if (branchNodes.size() > 0) {
            outgoingFlow0 = this.createSequenceFlow(startGatewayFlowId, branchNodes.get(0).getKey());
        } else {
            outgoingFlow0 = this.createSequenceFlow(startGatewayFlowId, endGatewayFlowId);
        }
        String itemIndexVarName = LoopVarServiceTask.getIndexVarName(nodeConfig);
        String itemSizeVarName = LoopVarServiceTask.getSizeVarName(nodeConfig);
        outgoingFlow0.setConditionExpression("${" + itemIndexVarName + " < " + itemSizeVarName + "}");

        // 建立开始网关到结束网关的出边
        SequenceFlow outgoingFlow1 = this.createSequenceFlow(startGatewayFlowId, endGatewayFlowId);
        startGateway.setOutgoingFlows(Arrays.asList(outgoingFlow0, outgoingFlow1));

        this.process.addFlowElement(startGateway);

        return true;
    }

    @Override
    public void endVisit(LoopBranchNodeConfig nodeConfig) {
        // 生成结束网关（1入边2出边）
        ExclusiveGateway endGateway = new ExclusiveGateway();
        String startGatewayFlowId = this.getStartGatewayFlowId(nodeConfig);
        String endGatewayFlowId = this.getEndGatewayFlowId(nodeConfig);
        endGateway.setId(endGatewayFlowId);
        endGateway.setName(nodeConfig.getName() + "-end");

        List<SequenceFlow> startOutgoingFlows = this.getGatewayFlowFromProcess(startGatewayFlowId).getOutgoingFlows();
        List<AbstractNodeConfig> branchNodes = nodeConfig.getNodes();
        int branchNodeSize = branchNodes.size();
        SequenceFlow incomingFlow0 = null;
        if (branchNodeSize > 0) {
            // 建立循环分支最后一个节点到结束网关的入边incomingFlow0
            String lastBranchNodeKey = branchNodes.get(branchNodeSize - 1).getKey();
            incomingFlow0 = this.createSequenceFlow(lastBranchNodeKey, endGatewayFlowId);
        } else {
            // 直接取开始网关的出边outgoingFlow0
            incomingFlow0 = startOutgoingFlows.get(0);
        }
        // 取开始网关的出边outgoingFlow1作为结束网关的入边incomingFlow1
        SequenceFlow incomingFlow1 = startOutgoingFlows.get(1);

        // 设置结束网关的入边
        endGateway.setIncomingFlows(Arrays.asList(incomingFlow0, incomingFlow1));

        // 建立结束网关跳转下一节点的出边
        String nextFlowId = this.getNextNodeFlowId(nodeConfig);
        SequenceFlow outgoingFlow0 = this.createSequenceFlow(endGatewayFlowId, nextFlowId);
        String itemIndexVarName = LoopVarServiceTask.getIndexVarName(nodeConfig);
        String itemSizeVarName = LoopVarServiceTask.getSizeVarName(nodeConfig);
        outgoingFlow0.setConditionExpression("${" + itemIndexVarName + " >= " + itemSizeVarName + "}");

        // 建立结束网关跳转变量节点的出边
        String varServiceTaskFlowId = LoopVarServiceTask.getVarTaskFlowId(nodeConfig);
        SequenceFlow outgoingFlow1 = this.createSequenceFlow(endGatewayFlowId, varServiceTaskFlowId);

        // 设置结束网关的出边
        endGateway.setOutgoingFlows(Arrays.asList(outgoingFlow0, outgoingFlow1));

        this.process.addFlowElement(endGateway);
    }

    @Override
    public boolean visit(GatewayNodeConfig nodeConfig) {
        // 生成开始网关
        ExclusiveGateway startGateway = new ExclusiveGateway();
        String startGatewayFlowId = this.getStartGatewayFlowId(nodeConfig);
        startGateway.setId(startGatewayFlowId);
        startGateway.setName(nodeConfig.getName() + "-start");
        List<SequenceFlow> incomingFlows = new ArrayList<>();
        incomingFlows.add(this.createSequenceFlow(nodeConfig.getPrevNode().getKey(), startGatewayFlowId));
        startGateway.setIncomingFlows(incomingFlows);
        List<ConditionBranchNodeConfig> branches = nodeConfig.getBranches();
        List<SequenceFlow> outgoingFlows = new ArrayList<>();
        for (ConditionBranchNodeConfig branch : branches) {
            List<AbstractNodeConfig> branchNodes = branch.getNodes();
            if (branchNodes.size() > 0) { // 可能存在一些没有节点的分支
                String firstNodeKey = branchNodes.get(0).getKey();
                SequenceFlow outgoingFlow = this.createSequenceFlow(startGatewayFlowId, firstNodeKey);
                outgoingFlow.setConditionExpression(branch.getCondition().getExpression());
                outgoingFlows.add(outgoingFlow);
            }
        }
        startGateway.setOutgoingFlows(outgoingFlows);
        process.addFlowElement(startGateway);

        return true;
    }

    @Override
    public void endVisit(GatewayNodeConfig nodeConfig) {
        // 生成结束网关
        ExclusiveGateway endGateway = new ExclusiveGateway();
        String endGatewayFlowId = this.getEndGatewayFlowId(nodeConfig);
        endGateway.setId(endGatewayFlowId);
        endGateway.setName(nodeConfig.getName() + "-end");
        List<ConditionBranchNodeConfig> branches = nodeConfig.getBranches();
        List<SequenceFlow> ingoingFlows = new ArrayList<>();
        for (ConditionBranchNodeConfig branch : branches) {
            List<AbstractNodeConfig> branchNodes = branch.getNodes();
            if (branchNodes.size() > 0) { // 可能存在一些没有节点的分支
                String lastNodeKey = branchNodes.get(branchNodes.size() - 1).getKey();
                SequenceFlow ingoingFlow = this.createSequenceFlow(lastNodeKey, endGatewayFlowId);
                ingoingFlows.add(ingoingFlow);
            } else {
                // 分支不存在节点时，直接开始网关连接结束网关
                String startGatewayFlowId = this.getStartGatewayFlowId(nodeConfig);
                SequenceFlow ingoingFlow = this.createSequenceFlow(startGatewayFlowId, endGatewayFlowId);
                ingoingFlows.add(ingoingFlow);
            }
        }
        endGateway.setIncomingFlows(ingoingFlows);
        List<SequenceFlow> outgoingFlows = new ArrayList<>();
        outgoingFlows.add(this.createSequenceFlow(endGatewayFlowId, this.getNextNodeFlowId(nodeConfig)));
        endGateway.setOutgoingFlows(outgoingFlows);
        process.addFlowElement(endGateway);
    }

    /**
     * 创建用户任务
     *
     * @param nodeConfig 用户节点配置
     * @return 用户任务
     */
    protected UserTask createUserTask(AbstractUserTaskNodeConfig nodeConfig) {
        UserTask userTask = new UserTask();
        this.initCommonInfos(userTask, nodeConfig);
        userTask.setAssignee(nodeConfig.getAssignee().getValue().toString());
        return userTask;
    }

    /**
     * 创建程序任务
     *
     * @param nodeConfig 用户节点配置
     * @return 用户任务
     */
    protected ServiceTask createServiceTask(ServiceTaskNodeConfig nodeConfig) {
        ServiceTask serviceTask = new ServiceTask();
        this.initCommonInfos(serviceTask, nodeConfig);
        serviceTask.setImplementationType("class");
        serviceTask.setImplementation(ActivitiBpmServiceTask.class.getName());
        // TODO 放在documentation不妥（documentation应该是描述信息），但是放在Attribute 或
        // OperationRef中的话在ActivitiBpmServiceTask中又取不到
        serviceTask.setDocumentation(nodeConfig.getClassName());
        return serviceTask;
    }

    /**
     * 创建前一个节点到指定节点的边
     *
     * @param nodeConfig
     */
    protected void createSequenceFlowFromPrev(AbstractNodeConfig nodeConfig) {
        AbstractNodeConfig prevNodeConfig = nodeConfig.getPrevNode();
        if (prevNodeConfig != null) {
            // 网关节点、循环节点已经由结束网关拉了出边，不需要再拉边
            NodeTypeEnum prevNodeType = prevNodeConfig.getNodeType();
            if (prevNodeType != NodeTypeEnum.GATEWAY && prevNodeType != NodeTypeEnum.LOOP) {
                String prevNodeKey = this.getPrevNodeFlowId(nodeConfig);
                this.createSequenceFlow(prevNodeKey, nodeConfig.getKey());
            }
        }
    }

    /**
     * 获取节点的上一个节点作为源边时的Flow ID
     *
     * @param nodeConfig
     * @return
     */
    private String getPrevNodeFlowId(AbstractNodeConfig nodeConfig) {
        AbstractNodeConfig prevNodeConfig = nodeConfig.getPrevNode();
        if (prevNodeConfig.getNodeType() != NodeTypeEnum.GATEWAY) {
            return prevNodeConfig.getKey();
        }

        return this.getEndGatewayFlowId(prevNodeConfig);
    }

    /**
     * 获取节点的下一个节点作为目标边时的Flow ID
     *
     * @param nodeConfig
     * @return
     */
    private String getNextNodeFlowId(AbstractNodeConfig nodeConfig) {
        AbstractNodeConfig nextNodeConfig = nodeConfig.getNextNode();
        if (nextNodeConfig.getNodeType() != NodeTypeEnum.GATEWAY) {
            return nextNodeConfig.getKey();
        }

        return this.getStartGatewayFlowId(nextNodeConfig);
    }

    /**
     * 获取开始网关的Flow ID
     *
     * @param nodeConfig
     * @return
     */
    private String getStartGatewayFlowId(AbstractNodeConfig nodeConfig) {
        return nodeConfig.getKey() + "-start";
    }

    /**
     * 获取结束网关的Flow ID
     *
     * @param nodeConfig
     * @return
     */
    private String getEndGatewayFlowId(AbstractNodeConfig nodeConfig) {
        return nodeConfig.getKey() + "-end";
    }

    /**
     * 获取流程中已经存在的网关
     *
     * @param flowId 网关节点FlowId
     * @return SequenceFlow
     */
    private Gateway getGatewayFlowFromProcess(String flowId) {
        return (Gateway) this.process.getFlowElement(flowId);
    }

    /**
     * 根据开始节点与结束节点ID获取流程中已经存在的边
     *
     * @param fromId 开始结点FlowId
     * @param toId 结束节点FlowId
     * @return SequenceFlow
     */
    private SequenceFlow getSequenceFlowFromProcess(String fromId, String toId) {
        String flowId = this.getSequenceId(fromId, toId);
        return (SequenceFlow) this.process.getFlowElement(flowId);
    }

    /**
     * 创建两个节点的边
     *
     * @param fromId 开始节点ID
     * @param toId   结束节点ID
     * @return 边的实例
     */
    protected SequenceFlow createSequenceFlow(String fromId, String toId) {
        SequenceFlow flow = new SequenceFlow();
        flow.setId(this.getSequenceId(fromId, toId));
        flow.setSourceRef(fromId);
        flow.setTargetRef(toId);
        this.process.addFlowElement(flow);
        return flow;
    }

    /**
     * 生成边的Flow ID
     * @param fromId 开始节点FlowId
     * @param toId 结束节点FlowId
     * @return
     */
    private String getSequenceId(String fromId, String toId) {
        return fromId + "_" + toId;
    }

    /**
     * 初始化工具的信息
     *
     * @param flowElement
     * @param nodeConfig
     */
    private void initCommonInfos(FlowElement flowElement, AbstractNodeConfig nodeConfig) {
        flowElement.setId(nodeConfig.getKey());
        flowElement.setName(nodeConfig.getName());
        // flowElement.setDocumentation(nodeConfig.getDescription());
    }

}
