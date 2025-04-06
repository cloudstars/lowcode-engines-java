package io.github.cloudstars.lowcode.bpm.vendor.query;

import java.util.Date;

/**
 * 任务信息
 *
 * @author clouds
 */
public class TaskInfo {

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
     * 所有人
     */
    private String owner;

    /**
     * 处理人
     */
    private String assignee;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 表单编号
     */
    private String formKey;

    /**
     * 业务编号
     */
    private String businessKey;

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    /*String getId();

    String getName();

    String getDescription();

    int getPriority();

    String getOwner();

    String getAssignee();

    String getProcessInstanceId();

    String getExecutionId();

    String getProcessDefinitionId();

    Date getCreateTime();

    String getTaskDefinitionKey();

    Date getDueDate();

    String getCategory();

    String getParentTaskId();

    String getTenantId();

    String getFormKey();

    Map<String, Object> getTaskLocalVariables();

    Map<String, Object> getProcessVariables();

    Date getClaimTime();

    String getBusinessKey();*/
}
