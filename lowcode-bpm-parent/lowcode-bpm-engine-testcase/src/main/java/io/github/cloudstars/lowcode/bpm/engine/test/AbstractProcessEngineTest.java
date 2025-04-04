package io.github.cloudstars.lowcode.bpm.engine.test;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.vendor.BpmDeployProvider;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.junit.Assert;

import javax.annotation.Resource;

/**
 * 抽象的流程引擎测试类，定义了测试的规范（输入/输出）
 *
 * @author clouds
 */
public abstract class AbstractProcessEngineTest {

    @Resource
    private BpmDeployProvider provider;

    /**
     * 测试部署流程定义（简单不含分支的流程）
     */
    protected void testDeploySimple1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("process/simple1.json");
        ProcessConfig config = new ProcessConfig(configJson);
        String deployId = this.provider.deploy(config);
        // 此次简单断言部署ID不为NULL
        Assert.assertNotNull(deployId);
    }

    /**
     * 测试部署流程定义（简单的含分支的流程）
     */
    protected void testDeploySimpleBranch() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("process/simple-branch.json");
        ProcessConfig config = new ProcessConfig(configJson);
        String deployId = this.provider.deploy(config);
        // 此次简单断言部署ID不为NULL
        Assert.assertNotNull(deployId);
    }

}
