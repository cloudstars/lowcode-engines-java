package io.github.cloudstars.lowcode.bpm.provider.activiti;

import io.github.cloudstars.lowcode.ActivitiBpmProviderTestApplication;
import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiBpmProviderTestApplication.class)
public class ProcessBuildBpmNodeVisitorTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("process/simple1.json");
        ProcessConfig processConfig = new ProcessConfig(configJson);
        Process process = new Process();
        ProcessBuildBpmNodeVisitor visitor = new ProcessBuildBpmNodeVisitor(process);
        processConfig.getMainBranch().accept(visitor);
        Collection<FlowElement> flowElements = process.getFlowElements();
        Assert.assertEquals(7, flowElements.size());
        // TODO 作断言
    }

}
