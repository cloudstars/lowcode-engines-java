package io.github.cloudstars.lowcode.bpm.commons.visitor;

import io.github.cloudstars.lowcode.BpmCommonsTestApplication;
import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 流程配置打印访问器测试类
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BpmCommonsTestApplication.class)
public class ProcessConfigOutputVisitorTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("process/simple1.json");
        ProcessConfig config = new ProcessConfig(configJson);
        StringBuilder builder = new StringBuilder();
        ProcessConfigOutputVisitor visitor = new ProcessConfigOutputVisitor(builder);
        config.getMainBranch().accept(visitor);
        String output = builder.toString();
        Assert.assertEquals("Start: startUser Approve: user-task1End: end", output);
    }

}
