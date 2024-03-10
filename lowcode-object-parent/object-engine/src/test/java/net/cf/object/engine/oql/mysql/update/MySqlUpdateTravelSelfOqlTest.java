package net.cf.object.engine.oql.mysql.update;

import net.cf.object.engine.ObjectEngineTestApplication;
import net.cf.object.engine.oql.testcase.update.AbstractUpdateTravelSelfRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineTestApplication.class)
public class MySqlUpdateTravelSelfOqlTest extends AbstractUpdateTravelSelfRepoTest {

    @Test
    @Override
    public void testUpdateTravelById() {
        super.testUpdateTravelById();
    }

}
