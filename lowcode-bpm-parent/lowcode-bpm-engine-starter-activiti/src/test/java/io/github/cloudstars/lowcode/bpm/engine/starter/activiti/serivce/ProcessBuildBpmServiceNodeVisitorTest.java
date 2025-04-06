
package io.github.cloudstars.lowcode.bpm.engine.starter.activiti.serivce;

import io.github.cloudstars.lowcode.ActivitiBpmEngineStarterTestApplication;
import io.github.cloudstars.lowcode.bpm.engine.test.ProcessClassPaths;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiBpmEngineStarterTestApplication.class)
public class ProcessBuildBpmServiceNodeVisitorTest {

    @Test
    public void testSimple1() {
        Process process = ProcessLoadUtils.loadFromClassPath(ProcessClassPaths.SERVICE_SIMPLE1);
        Collection<FlowElement> flowElements = process.getFlowElements();
        // 1个开始、1个结束、1个程序、2条边
        Assert.assertEquals(1, flowElements.stream().filter((f) -> f instanceof StartEvent).count());
        Assert.assertEquals(1, flowElements.stream().filter((f) -> f instanceof EndEvent).count());
        Assert.assertEquals(1, flowElements.stream().filter((f) -> f instanceof ServiceTask).count());
        Assert.assertEquals(2, flowElements.stream().filter((f) -> f instanceof SequenceFlow).count());
        Assert.assertEquals(5, flowElements.size());
    }

}
