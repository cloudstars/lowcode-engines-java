package net.cf.object.engine.oql.mysql.select.detail;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.select.detail.AbstractSelectTravelSelfDetailRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlSelectTravelSelfDetailOqlTest extends AbstractSelectTravelSelfDetailRepoTest {

    @Test
    @Override
    public void testSelectTravelAndTripIdsById() {
        super.testSelectTravelAndTripIdsById();
    }

    @Test
    @Override
    public void testSelectTravelAndTripById() {
        super.testSelectTravelAndTripById();
    }

    @Test
    @Override
    public void testSelectTravelAndTripByIdVars() {
        super.testSelectTravelAndTripByIdVars();
    }

    @Test
    @Override
    public void testSelectTravelAndTripList() {
        super.testSelectTravelAndTripList();
    }

    @Test
    @Override
    public void testSelectTravelAndTripListVars() {
        super.testSelectTravelAndTripListVars();
    }
}
