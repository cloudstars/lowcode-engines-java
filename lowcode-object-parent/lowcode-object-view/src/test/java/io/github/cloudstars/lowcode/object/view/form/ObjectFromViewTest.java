package io.github.cloudstars.lowcode.object.view.form;


import io.github.cloudstars.lowcode.ObjectViewTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectViewTestApplication.class)
public class ObjectFromViewTest {

    @Resource
    private ObjectFormView formView;

    @Test
    public void test1() {
    }

}
