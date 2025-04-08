package io.github.cloudstars.lowcode.bpm.vendor.activiti.service;

import io.github.cloudstars.lowcode.bpm.commons.config.loop.LoopBranchNodeConfig;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.List;

/**
 * 循环节点的变量初始化、更新程序任务
 *
 * @author clouds
 */
public class LoopVarServiceTask implements JavaDelegate {

    public static final String VAR_SEPARATOR = "##";

    public static final String getVarTaskFlowId(LoopBranchNodeConfig nodeConfig) {
        String varServiceTaskId = nodeConfig.getKey() + "-var";
        return varServiceTaskId;
    }

    public static final String getIndexVarName(LoopBranchNodeConfig nodeConfig) {
        return nodeConfig.getItem() + "_index";
    }

    public static final String getSizeVarName(LoopBranchNodeConfig nodeConfig) {
         return nodeConfig.getItem() + "_size";
    }



    @Override
    public void execute(DelegateExecution delegateExecution) {
        ServiceTask serviceTask = (ServiceTask) delegateExecution.getCurrentFlowElement();
        String[] varInfos = serviceTask.getDocumentation().split(VAR_SEPARATOR);
        String loopVarsName = varInfos[0];
        String loopVarName = varInfos[1];
        Integer loopCount = (Integer) delegateExecution.getVariable(loopVarName + "_index", false);
        if (loopCount == null) {
            // 变量不存在则定义且初始化为0，并初始化size变量
            loopCount = 0;
            List loopVarsValue = (List) delegateExecution.getVariable(loopVarsName, false);
            delegateExecution.setVariable(loopVarName + "_size", loopVarsValue.size());
        } else {
            // 变量存在则 + 1
            loopCount = loopCount + 1;
        }
        delegateExecution.setVariable(loopVarName + "_index", loopCount);
    }

}
