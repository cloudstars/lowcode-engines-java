package io.github.cloudstars.lowcode.bpm.vendor.activiti.service;

import io.github.cloudstars.lowcode.bpm.vendor.service.BpmServiceTask;
import io.github.cloudstars.lowcode.bpm.vendor.service.BpmDelegateExecution;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Activiti流程引擎的ServiceTask
 *
 * @author clouds
 */
public class ActivitiBpmServiceTask implements JavaDelegate {

    public static final String ATTR_PROXY = "proxy";
    public static final String ATTR_CLASSNAME = "classname";

    private BpmServiceTask bpmServiceTask;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        if (bpmServiceTask == null) {
            synchronized (this) {
                if (bpmServiceTask == null) {
                    ServiceTask serviceTask = (ServiceTask) delegateExecution.getCurrentFlowElement();
                    String proxyClassName = serviceTask.getDocumentation();
                    bpmServiceTask = (BpmServiceTask) ObjectUtils.newInstance(proxyClassName);
                }
            }
        }


        BpmDelegateExecution bpmDelegateExecution = this.toBpmDelegateExecution(delegateExecution);
        this.bpmServiceTask.executeTask(bpmDelegateExecution);
    }

    /**
     * 将Activiti的执行流转换为Bpm引擎的执行流
     *
     * @param delegateExecution
     * @return
     */
    private BpmDelegateExecution toBpmDelegateExecution(DelegateExecution delegateExecution) {
        BpmDelegateExecution bpmDelegateExecution = new BpmDelegateExecution();
        bpmDelegateExecution.setProcessInstanceId(delegateExecution.getProcessInstanceId());
        return bpmDelegateExecution;
    }

}
