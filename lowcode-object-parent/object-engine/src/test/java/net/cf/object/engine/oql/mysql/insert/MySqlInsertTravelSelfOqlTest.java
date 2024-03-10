package net.cf.object.engine.oql.mysql.insert;

import net.cf.object.engine.ObjectEngineTestApplication;
import net.cf.object.engine.oql.testcase.insert.AbstractInsertTravelSelfRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineTestApplication.class)
public class MySqlInsertTravelSelfOqlTest extends AbstractInsertTravelSelfRepoTest {

    @Test
    @Override
    public void testInsertTravel() {
        super.testInsertTravel();
    }

}
