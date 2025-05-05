package io.github.cloudstars.lowcode.object.view.engine.table;


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
public class ObjectTableViewTest {

    @Resource
    private ObjectTableView objectTableView;

    @Test
    public void test1() {
    }

}
