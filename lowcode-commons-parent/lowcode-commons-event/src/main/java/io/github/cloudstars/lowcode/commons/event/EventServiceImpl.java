package io.github.cloudstars.lowcode.commons.event;

import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 事件总线服务实现
 *
 * @author clouds
 */
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    /**
     * 事件发送器列表
     */
    private List<EventSender> senders = new ArrayList<>();

    /**
     * 事件处理器列表
     */
    private List<EventHandler> handlers = new ArrayList<>();

    @Override
    public int emit(Event event) {
        EventSender sender = this.senders.stream().filter((s -> s.accept(event))).findFirst().get();
        if (sender == null) {
            throw new RuntimeException("找不到合适的事件发送器, event：" + JsonUtils.toJsonString(event));
        }

        int msgId = sender.send(event);
        logger.info("事件发送成功, 结束ID：{}", msgId);
        return msgId;
    }

    @Override
    public int receive(Event event) {
        List<EventHandler> targetHandlers = this.handlers.stream().filter((s -> s.accept(event))).collect(Collectors.toList());
        int handlerSize = targetHandlers.size();
        logger.debug("总有{}个事件处理器对此事件[id={}]响应", handlerSize, event.getId());
        if (handlerSize > 0) {
            targetHandlers.forEach((h) -> {
                h.handle(event);
            });
        }

        return 0;
    }

    @Override
    public void addSender(EventSender sender) {
        this.senders.add(sender);
    }

    @Override
    public void addHandler(EventHandler handler) {
        this.handlers.add(handler);
    }

}
