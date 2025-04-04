package io.github.cloudstars.lowcode.bpm.provider.activiti;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.GatewayBranchNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.gateway.GatewayNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.start.StartNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.AbstractUserNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserApproveNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.user.UserWriteNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;

import java.util.ArrayList;
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
    public boolean visit(StartNodeConfig nodeConfig) {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(nodeConfig.getKey());
        startEvent.setName(nodeConfig.getName());
        this.process.addFlowElement(startEvent);

        return false;
    }

    @Override
    public boolean visit(UserApproveNodeConfig nodeConfig) {
        // 创建用户审批任务
        UserTask userTask = this.createUserTask(nodeConfig);
        process.addFlowElement(userTask);

        // 创建前一个节点到当前用户审批节点的边
        this.createSequenceFlowFromPrev(nodeConfig);

        return false;
    }

    @Override
    public boolean visit(UserWriteNodeConfig nodeConfig) {
        // 创建用户填写任务
        UserTask userTask = this.createUserTask(nodeConfig);
        process.addFlowElement(userTask);

        // 创建前一个节点到当前用户填写节点的边
        this.createSequenceFlowFromPrev(nodeConfig);

        return false;
    }

    @Override
    public boolean visit(EndNodeConfig nodeConfig) {
        String nodeKey = nodeConfig.getKey();

        // 创建结束事件
        EndEvent endEvent = new EndEvent();
        endEvent.setId(nodeKey);
        endEvent.setName(nodeConfig.getName());
        this.process.addFlowElement(endEvent);

        // 创建前一个节点到当前用户审批节点的边
        this.createSequenceFlowFromPrev(nodeConfig);

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

    @Override
    public boolean visit(GatewayNodeConfig nodeConfig) {
        // 生成开始网关
        ExclusiveGateway startGateway = new ExclusiveGateway();
        String startGatewayKey = this.getStartGatewayId(nodeConfig);
        startGateway.setId(startGatewayKey);
        startGateway.setName(nodeConfig.getName() + "_start");
        List<SequenceFlow> incomingFlows = new ArrayList<>();
        incomingFlows.add(this.createSequenceFlow(nodeConfig.getPrevNode().getKey(), startGatewayKey));
        startGateway.setIncomingFlows(incomingFlows);
        List<GatewayBranchNodeConfig> branches = nodeConfig.getBranches();
        List<SequenceFlow> outgoingFlows = new ArrayList<>();
        for (GatewayBranchNodeConfig branch : branches) {
            List<AbstractNodeConfig> branchNodes = branch.getNodes();
            if (branchNodes.size() > 0) { // 可能存在一些没有节点的分支
                String firstNodeKey = branchNodes.get(0).getKey();
                SequenceFlow outgoingFlow = this.createSequenceFlow(startGatewayKey, firstNodeKey);
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
        String endGatewayKey = this.getEndGatewayKey(nodeConfig);
        endGateway.setId(endGatewayKey);
        endGateway.setName(nodeConfig.getName() + "_end");
        List<GatewayBranchNodeConfig> branches = nodeConfig.getBranches();
        List<SequenceFlow> ingoingFlows = new ArrayList<>();
        for (GatewayBranchNodeConfig branch : branches) {
            List<AbstractNodeConfig> branchNodes = branch.getNodes();
            if (branchNodes.size() > 0) { // 可能存在一些没有节点的分支
                String lastNodeKey = branchNodes.get(branchNodes.size() - 1).getKey();
                SequenceFlow ingoingFlow = this.createSequenceFlow(lastNodeKey, endGatewayKey);
                ingoingFlows.add(ingoingFlow);
            } else {
                // 分支不存在节点时，直接开始网关连接结束网关
                String startGatewayKey = this.getStartGatewayId(nodeConfig);
                SequenceFlow ingoingFlow = this.createSequenceFlow(startGatewayKey, endGatewayKey);
                ingoingFlows.add(ingoingFlow);
            }
        }
        endGateway.setIncomingFlows(ingoingFlows);
        List<SequenceFlow> outgoingFlows = new ArrayList<>();
        outgoingFlows.add(this.createSequenceFlow(endGatewayKey, nodeConfig.getNextNode().getKey()));
        endGateway.setOutgoingFlows(outgoingFlows);
        process.addFlowElement(endGateway);
    }

    /**
     * 获取开始网关的ID
     *
     * @param nodeConfig
     * @return
     */
    private String getStartGatewayId(GatewayNodeConfig nodeConfig) {
        return nodeConfig.getKey() + "_start";
    }

    /**
     * 获取结束网关的ID
     *
     * @param nodeConfig
     * @return
     */
    private String getEndGatewayKey(GatewayNodeConfig nodeConfig) {
        return nodeConfig.getKey() + "_end";
    }

    /**
     * 创建前一个节点到指定节点的边
     *
     * @param nodeConfig
     */
    protected void createSequenceFlowFromPrev(NodeConfig nodeConfig) {
        AbstractNodeConfig aNodeConfig = (AbstractNodeConfig) nodeConfig;
        AbstractNodeConfig prevNodeConfig = (AbstractNodeConfig) aNodeConfig.getPrevNode();
        if (prevNodeConfig != null) {
            // 网关节点已经由结束网关拉出边，不需要再拉边
            if (prevNodeConfig.getNodeType() != NodeTypeEnum.GATEWAY) {
                String nodeKey = nodeConfig.getKey();
                String prevNodeKey = prevNodeConfig.getKey();
                this.createSequenceFlow(prevNodeKey, nodeKey);
            }
        }
    }

    /**
     * 创建两个节点的边
     *
     * @param fromId 开始节点ID
     * @param toId 结束节点ID
     * @return 边的实例
     */
    protected SequenceFlow createSequenceFlow(String fromId, String toId) {
        SequenceFlow flow = new SequenceFlow();
        flow.setId(fromId + "_" + toId);
        flow.setSourceRef(fromId);
        flow.setTargetRef(toId);
        this.process.addFlowElement(flow);
        return flow;
    }

}
