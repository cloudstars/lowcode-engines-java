package net.cf.commons.lang;

/**
 * 操作人Bean
 *
 * @author clouds
 */
public class Operator {

    /**
     * 租户号
     */
    private String tenantId;

    /**
     * 用户号
     */
    private String key;

    /**
     * 用户名
     */
    private String name;

    public Operator() {
    }

    public Operator(String tenantId, String key, String name) {
        this.tenantId = tenantId;
        this.key = key;
        this.name = name;
    }

    public Operator(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
