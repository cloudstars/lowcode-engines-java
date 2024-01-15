package com.cmbchina.codefriend.commons.lang;

public class Operator {

    /**
     * 租户号
     */
    private String tenantId;

    /**
     * 用户号
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    public Operator() {
    }

    public Operator(String tenantId, String userId, String userName) {
        this.tenantId = tenantId;
        this.userId = userId;
        this.userName = userName;
    }

    public Operator(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
