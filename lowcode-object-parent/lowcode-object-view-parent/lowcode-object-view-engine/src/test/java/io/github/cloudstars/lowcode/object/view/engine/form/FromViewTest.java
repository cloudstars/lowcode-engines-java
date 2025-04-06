package io.github.cloudstars.lowcode.object.view.engine.form;


import io.github.cloudstars.lowcode.ObjectViewEngineTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectViewEngineTestApplication.class)
public class FromViewTest {

    @Resource
    private FormView formView;

    @Test
    public void test1() {
    }

}
