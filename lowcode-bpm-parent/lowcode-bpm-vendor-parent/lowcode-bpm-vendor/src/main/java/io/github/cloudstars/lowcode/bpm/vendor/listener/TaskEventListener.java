package io.github.cloudstars.lowcode.bpm.vendor.listener;

/**
 * 任务事件监听器
 *
 * @author clouds
 */
public interface TaskEventListener {

    /**
     * 处理任务事件
     *
     * @param context 任务上下文
     */
    void handle(TaskContext context);

}
