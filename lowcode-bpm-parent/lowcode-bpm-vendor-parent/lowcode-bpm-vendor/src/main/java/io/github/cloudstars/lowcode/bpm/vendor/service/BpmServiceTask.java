package io.github.cloudstars.lowcode.bpm.vendor.service;

/**
 * 程序任务接口
 *
 * @author clouds
 */
public interface BpmServiceTask {

    /**
     * 执行任务
     *
     * @param execution 执行流上下文
     */
    void executeTask(BpmDelegateExecution execution);

}
