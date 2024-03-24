package net.cf.object.engine.oql.mysql.select;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectTravelSelfCountLimitRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlSelectTravelSelfCountLimitOqlTest extends AbstractSelectTravelSelfCountLimitRepoTest {

    @Test
    @Override
    public void testSelectCountOneTravel() {
        super.testSelectCountOneTravel();
    }

    @Test
    @Override
    public void testSelectCountStarTravel() {
        super.testSelectCountStarTravel();
    }

    @Test
    @Override
    public void testSelectCountFieldTravel() {
        super.testSelectCountFieldTravel();
    }

    @Test
    @Override
    public void testSelectTravelWithLimit() {
        super.testSelectTravelWithLimit();
    }

    @Test
    @Override
    public void testSelectTravelWithLimitOffset() {
        super.testSelectTravelWithLimitOffset();
    }
}
