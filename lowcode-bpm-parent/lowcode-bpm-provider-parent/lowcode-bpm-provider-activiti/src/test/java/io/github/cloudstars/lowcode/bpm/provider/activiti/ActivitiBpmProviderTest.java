package io.github.cloudstars.lowcode.bpm.provider.activiti;

import io.github.cloudstars.lowcode.ActivitiBpmProviderTestApplication;
import io.github.cloudstars.lowcode.bpm.engine.test.AbstractProcessEngineTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiBpmProviderTestApplication.class)
public class ActivitiBpmProviderTest extends AbstractProcessEngineTest {

    @Override
    @Test
    public void testDeploy() {
        super.testDeploy();
    }

}
