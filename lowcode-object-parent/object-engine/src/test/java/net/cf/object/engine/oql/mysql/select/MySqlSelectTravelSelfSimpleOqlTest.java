package net.cf.object.engine.oql.mysql.select;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectTravelSelfSimpleRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlSelectTravelSelfSimpleOqlTest extends AbstractSelectTravelSelfSimpleRepoTest {

    @Test
    @Override
    public void testSelectTravelOne() {
        super.testSelectTravelOne();
    }

    @Test
    @Override
    public void testSelectTravelOneVars() {
        super.testSelectTravelOneVars();
    }

    @Test
    @Override
    public void testSelectTravelList() {
        super.testSelectTravelList();
    }

    @Test
    @Override
    public void testSelectTravelListVars() {
        super.testSelectTravelListVars();
    }

    @Test
    @Override
    public void testSelectTravelInList() {
        super.testSelectTravelInList();
    }

    @Test
    @Override
    public void testSelectTravelInListVars() {
        super.testSelectTravelInListVars();
    }

    @Test
    @Override
    public void testSelectTravelLikeList() {
        super.testSelectTravelLikeList();
    }

    @Test
    @Override
    public void testSelectTravelLikeListVars() {
        super.testSelectTravelLikeListVars();
    }
}
