package io.github.cloudstars.lowcode.bpm.engine.test;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployService;
import org.junit.Assert;

import javax.annotation.Resource;

/**
 * 抽象的部署服务测试类，定义了测试的规范（输入/输出）
 *
 * @author clouds
 */
public abstract class AbstractDeployServiceTest {

    @Resource
    protected BpmDeployService deployService;

    /**
     * 测试部署流程定义（简单不含分支的流程）
     */
    protected void testDeploySimple1() {
        ProcessConfig config = ProcessConfigUtils.loadFromClassPath(ProcessClassPaths.USER_SIMPLE1);
        String deployId = this.deployService.deploy(config);
        // 此次简单断言部署ID不为NULL
        Assert.assertNotNull(deployId);
    }

    /**
     * 测试部署流程定义（简单的含分支的流程）
     */
    protected void testDeploySimpleBranch() {
        ProcessConfig config = ProcessConfigUtils.loadFromClassPath(ProcessClassPaths.USER_SIMPLE_BRANCH);
        String deployId = this.deployService.deploy(config);
        // 此次简单断言部署ID不为NULL
        Assert.assertNotNull(deployId);
    }

}
