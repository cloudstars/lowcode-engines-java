package io.github.cloudstars.lowcode.bpm.engine.test;

import io.github.cloudstars.lowcode.bpm.engine.service.BpmTaskService;

import javax.annotation.Resource;

/**
 * 抽象的流程服务测试类，定义了测试的规范（输入/输出）
 *
 * @author clouds
 */
public abstract class AbstractTaskServiceTest extends AbstractProcessServiceTest {

    @Resource
    private BpmTaskService taskService;

}
