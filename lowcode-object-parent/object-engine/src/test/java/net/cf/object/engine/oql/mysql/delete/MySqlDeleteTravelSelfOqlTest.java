package net.cf.object.engine.oql.mysql.delete;

import net.cf.object.engine.ObjectEngineTestApplication;
import net.cf.object.engine.oql.testcase.delete.AbstractDeleteTravelSelfRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineTestApplication.class)
public class MySqlDeleteTravelSelfOqlTest extends AbstractDeleteTravelSelfRepoTest {

    @Test
    @Override
    public void testDeleteTravelById() {
        super.testDeleteTravelById();
    }

}
