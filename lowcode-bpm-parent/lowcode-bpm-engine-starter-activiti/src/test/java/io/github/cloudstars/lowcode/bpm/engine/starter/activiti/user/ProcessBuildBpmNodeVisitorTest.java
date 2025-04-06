package io.github.cloudstars.lowcode.bpm.engine.starter.activiti.user;

import io.github.cloudstars.lowcode.ActivitiBpmEngineStarterTestApplication;
import io.github.cloudstars.lowcode.bpm.engine.starter.activiti.serivce.ProcessLoadUtils;
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
public class ProcessBuildBpmNodeVisitorTest {

    @Test
    public void testSimple1() {
        Process process = ProcessLoadUtils.loadFromClassPath(ProcessClassPaths.USER_SIMPLE1);
        Collection<FlowElement> flowElements = process.getFlowElements();
        // 1个开始、1个结束、2个人工、3条边
        Assert.assertEquals(1, flowElements.stream().filter((f) -> f instanceof StartEvent).count());
        Assert.assertEquals(1, flowElements.stream().filter((f) -> f instanceof EndEvent).count());
        Assert.assertEquals(2, flowElements.stream().filter((f) -> f instanceof UserTask).count());
        Assert.assertEquals(3, flowElements.stream().filter((f) -> f instanceof SequenceFlow).count());
        Assert.assertEquals(7, flowElements.size());
    }

    @Test
    public void testSimpleBranch() {
        Process process = ProcessLoadUtils.loadFromClassPath(ProcessClassPaths.USER_SIMPLE_BRANCH);
        Collection<FlowElement> flowElements = process.getFlowElements();
        // 1个开始、1个结束、2个网关、4个人工、8条边（网关的出入边不计入边）
        Assert.assertEquals(1, flowElements.stream().filter((f) -> f instanceof StartEvent).count());
        Assert.assertEquals(1, flowElements.stream().filter((f) -> f instanceof EndEvent).count());
        Assert.assertEquals(2, flowElements.stream().filter((f) -> f instanceof Gateway).count());
        Assert.assertEquals(1, flowElements.stream().filter((f) -> "u932k4k8fds11_start".equals(f.getId())).count());
        Gateway startGateway = (Gateway) flowElements.stream().filter((f) -> "u932k4k8fds11_start".equals(f.getId())).findFirst().get();
        Assert.assertEquals(1, startGateway.getIncomingFlows().size());
        Assert.assertEquals(2, startGateway.getOutgoingFlows().size());
        Assert.assertEquals(1, flowElements.stream().filter((f) -> "u932k4k8fds11_end".equals(f.getId())).count());
        Gateway endGateway = (Gateway) flowElements.stream().filter((f) -> "u932k4k8fds11_end".equals(f.getId())).findFirst().get();
        Assert.assertEquals(2, endGateway.getIncomingFlows().size());
        Assert.assertEquals(1, endGateway.getOutgoingFlows().size());
        Assert.assertEquals(4, flowElements.stream().filter((f) -> f instanceof UserTask).count());
        Assert.assertEquals(8, flowElements.stream().filter((f) -> f instanceof SequenceFlow).count());
        Assert.assertEquals(16, flowElements.size());
    }

}
