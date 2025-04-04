package io.github.cloudstars.lowcode.commons.event;

/**
 * 事件
 *
 * @param <T> 事件内容的类型
 */
public class Event<T> {

    /**
     * 事件的唯一标识
     */
    private String id;

    /**
     * 事件类型
     */
    private String type;

    /**
     * 事件的内容
     */
    private T payload;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

}
