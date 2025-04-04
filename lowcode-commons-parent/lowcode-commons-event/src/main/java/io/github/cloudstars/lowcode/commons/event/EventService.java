package io.github.cloudstars.lowcode.commons.event;

/**
 * 事件总线服务
 *
 * @author clouds
 */
public interface EventService {

    /**
     * 事件发送
     *
     * @param event 事件
     * @return 发送ID
     */
    int emit(Event event);

    /**
     * 回到一个事件
     *
     * @param event 事件
     * @return 接收ID
     */
    int receive(Event event);

    /**
     * 添加一个事件发送器
     *
     * @param sender 事件发送器
     */
    void addSender(EventSender sender);

    /**
     * 添加一个事件订阅器
     *
     * @param handler 事件处理器
     */
    void addHandler(EventHandler handler);
}
