package io.github.cloudstars.lowcode.bpm.engine.starter.activiti.user;

import io.github.cloudstars.lowcode.ActivitiBpmEngineStarterTestApplication;
import io.github.cloudstars.lowcode.bpm.engine.test.user.AbstractProcessServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiBpmEngineStarterTestApplication.class)
public class ActivitiProcessServiceImplTest extends AbstractProcessServiceTest {

    @Test
    @Override
    public void testStartSimple1() {
        super.testStartSimple1();
    }

}
