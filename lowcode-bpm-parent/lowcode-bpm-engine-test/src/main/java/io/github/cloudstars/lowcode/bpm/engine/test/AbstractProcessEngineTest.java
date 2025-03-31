package io.github.cloudstars.lowcode.bpm.engine.test;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmProvider;
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
    private BpmProvider provider;

    /**
     * 测试部署流程定义
     */
    protected void testDeploy() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("process/simple1.json");
        ProcessConfig config = new ProcessConfig(configJson);
        String deployId = this.provider.deploy(config);
        // 此次简单断言部署ID不为NULL
        Assert.assertNotNull(deployId);
    }

}
