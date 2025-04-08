package io.github.cloudstars.lowcode.bpm.vendor.service;

/**
 * BPM执行流
 *
 * @author clouds
 */
public class BpmDelegateExecution {

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
}
