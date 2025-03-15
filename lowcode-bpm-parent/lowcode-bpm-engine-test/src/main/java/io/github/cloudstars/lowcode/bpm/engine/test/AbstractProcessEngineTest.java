package io.github.cloudstars.lowcode.bpm.engine.test;

import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.editor.parser.ProcessConfigParser;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmProvider;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import org.junit.Assert;

import javax.annotation.Resource;

/**
 * 抽象的流程引擎测试类，定义了测试的规范（输入/输出）
 *
 * @author clouds
 */
public abstract class AbstractProcessEngineTest {

    @Resource
    private ProcessConfigParser parser;

    @Resource
    private BpmProvider provider;

    /**
     * 测试部署流程定义
     */
    protected void testDeploy() {
        String process = FileTestUtils.loadTextFromClasspath("process/process1.json");
        ProcessConfig config = this.parser.fromJsonString(process);
        String deployId = this.provider.deploy(config);
        // 此次简单断言部署ID不为NULL
        Assert.assertNotNull(deployId);
    }

}
