package io.github.cloudstars.lowcode.bpm.engine.provider.activiti;

import io.github.cloudstars.lowcode.ActivitiBpmEngineProviderTestApplication;
import io.github.cloudstars.lowcode.bpm.engine.test.AbstractProcessEngineTest;
import net.cf.bpm.engine.ProcessEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiBpmEngineProviderTestApplication.class)
public class ProcessEngineTest extends AbstractProcessEngineTest {

    @Resource
    private ProcessEngine processEngine;

    @Test
    @Override
    protected void testDeploy() {
        super.testDeploy();
    }

}
