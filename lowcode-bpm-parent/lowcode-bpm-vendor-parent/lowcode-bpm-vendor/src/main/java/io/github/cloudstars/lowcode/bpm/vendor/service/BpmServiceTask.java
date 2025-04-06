package io.github.cloudstars.lowcode.bpm.vendor.service;

/**
 * 程序任务接口
 *
 * @author clouds
 */
public interface BpmServiceTask {

    /**
     * 执行程序
     *
     * @param execution 执行流上下文
     */
    void execute(DelegateExecution execution);

}
