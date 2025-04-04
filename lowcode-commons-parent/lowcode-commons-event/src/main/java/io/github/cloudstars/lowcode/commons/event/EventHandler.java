package io.github.cloudstars.lowcode.commons.event;

/**
 * 事件处理器
 *
 * @author clouds 
 */
public interface EventHandler {

    /**
     * 获取事件处理器的ID
     *
     * @return
     */
    boolean getId();

    /**
     * 是否接受这个事件
     *
     * @param event
     * @return
     */
    boolean accept(Event event);

    /**
     * 处理事件
     *
     * @param event 事件
     */
   void handle(Event event);

}
