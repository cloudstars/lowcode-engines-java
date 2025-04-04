package io.github.cloudstars.lowcode.bpm.engine.test;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessService;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 抽象的流程服务测试类，定义了测试的规范（输入/输出）
 *
 * @author clouds
 */
public abstract class AbstractProcessServiceTest {

    @Resource
    private BpmDeployService deployService;

    @Resource
    private BpmProcessService processService;

    /**
     * 测试启动流程定义（简单不含分支的流程）
     */
    protected void testStartSimple1() {
        ProcessConfig config = ProcessConfigUtils.loadSimpleConfig();
        String processKey = config.getKey();
        // 先部署再启动流程
        this.deployService.deploy(config);
        this.processService.start(processKey, new HashMap<>());
    }

}
