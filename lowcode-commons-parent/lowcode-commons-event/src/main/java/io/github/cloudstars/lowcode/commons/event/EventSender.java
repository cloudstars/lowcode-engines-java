package io.github.cloudstars.lowcode.commons.event;

/**
 * 事件发送器
 *
 * @author clouds
 */
public interface EventSender {

    /**
     * 获取事件发送器的ID
     *
     * @return
     */
    boolean getId();

    /**
     * 是否接受这个事件
     *
     * @param event 事件
     * @return 是否接受该事件
     */
    boolean accept(Event event);

    /**
     * 发送一个事件
     *
     * @param event
     * @return 发送ID
     */
    int send(Event event);

}
