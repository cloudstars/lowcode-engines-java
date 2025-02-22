package net.cf.object.engine.oql.mysql.delete.detail;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.delete.detail.AbstractDeleteTravelDetailRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlDeleteTravelDetailOqlTest extends AbstractDeleteTravelDetailRepoTest {

    @Test
    @Override
    public void testDeleteTravelAndTripById() {
        super.testDeleteTravelAndTripById();
    }

    @Test
    @Override
    public void testDeleteTravelAndTripByIdVars() {
        super.testDeleteTravelAndTripByIdVars();
    }

    @Test
    @Override
    public void testDeleteTravelAndTripInIds() {
        super.testDeleteTravelAndTripInIds();
    }

    @Test
    @Override
    public void testDeleteTravelAndTripInIdsVars() {
        super.testDeleteTravelAndTripInIdsVars();
    }
}
