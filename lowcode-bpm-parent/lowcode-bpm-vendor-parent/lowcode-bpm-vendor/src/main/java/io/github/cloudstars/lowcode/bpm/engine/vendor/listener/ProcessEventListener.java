package io.github.cloudstars.lowcode.bpm.engine.vendor.listener;

/**
 * 流程事件监听器
 *
 * @author clouds
 */
public interface ProcessEventListener {

    /**
     * 处理流程事件
     *
     * @param context 任务上下文
     */
    void handle(ProcessContext context);

}
