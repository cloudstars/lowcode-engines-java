package net.cf.object.engine.oql.mysql;

import net.cf.object.engine.ObjectEngineTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectTravelSelfNoWhereRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineTestApplication.class)
public class MySQLSelectTravelSelfNoWhereOqlTest extends AbstractSelectTravelSelfNoWhereRepoTest {

    @Test
    @Override
    public void testSelectTravelList() {
        super.testSelectTravelList();
    }
}
