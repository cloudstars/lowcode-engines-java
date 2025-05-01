package io.github.cloudstars.lowcode.api.executor.invoke;

/**
 * HTTP头部
 *
 * @author clouds
 */
public class HttpHeader {

    /**
     * 头部名称
     */
    private String name;

    /**
     * 头部值
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
