package net.cf.object.engine.oql.mysql.update.detail;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.update.detail.AbstractUpdateTravelDetailRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlUpdateTravelDetailOqlTest extends AbstractUpdateTravelDetailRepoTest {

    @Test
    @Override
    public void testUpdateTravelAndTripByIdVars() {
        super.testUpdateTravelAndTripByIdVars();
    }

}
