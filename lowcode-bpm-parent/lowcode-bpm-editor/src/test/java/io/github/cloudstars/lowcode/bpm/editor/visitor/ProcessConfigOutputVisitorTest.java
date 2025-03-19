package io.github.cloudstars.lowcode.bpm.editor.visitor;

import io.github.cloudstars.lowcode.BpmEditorTestApplication;
import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.editor.parser.ProcessConfigParser;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import io.github.cloudstars.lowcode.commons.utils.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 流程配置打印访问器测试类
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BpmEditorTestApplication.class)
public class ProcessConfigOutputVisitorTest {

    @Resource
    private ProcessConfigParser parser;

    @Test
    public void test1() {
        String configJsonString = FileTestUtils.loadTextFromClasspath("process/simple1.json");
        ProcessConfig config = this.parser.fromJson(JsonUtils.parseObject(configJsonString));
        StringBuilder builder = new StringBuilder();
        ProcessConfigOutputVisitor visitor = new ProcessConfigOutputVisitor(builder);
        config.getMainBranch().accept(visitor);
        String output = builder.toString();
        Assert.assertEquals("Start: startUser Approve: user-task1End: end", output);
    }

}
