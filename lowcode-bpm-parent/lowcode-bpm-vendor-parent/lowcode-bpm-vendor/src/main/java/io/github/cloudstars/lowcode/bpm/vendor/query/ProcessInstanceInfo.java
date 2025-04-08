
package io.github.cloudstars.lowcode.bpm.vendor.query;

import io.github.cloudstars.lowcode.bpm.vendor.ProcessInstStatus;

import java.util.Date;

/**
 * 流程实例信息
 *
 * @author clouds
 */
public class ProcessInstanceInfo {

    /**
     * ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 启动人
     */
    private String starter;

    /**
     * 创建时间
     */
    private Date startTime;

    /**
     * 表单编号
     */
    private String processDefinitionKey;

    /**
     * 业务编号
     */
    private String businessKey;

    /**
     * 状态
     */
    private ProcessInstStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public ProcessInstStatus getStatus() {
        return status;
    }

    public void setStatus(ProcessInstStatus status) {
        this.status = status;
    }
}
