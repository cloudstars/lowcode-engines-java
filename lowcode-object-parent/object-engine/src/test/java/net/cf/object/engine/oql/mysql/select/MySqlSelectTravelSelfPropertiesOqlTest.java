package net.cf.object.engine.oql.mysql.select;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectTravelSelfPropertiesRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlSelectTravelSelfPropertiesOqlTest extends AbstractSelectTravelSelfPropertiesRepoTest {

    @Test
    @Override
    public void testSelectTravelCreatorListById() {
        super.testSelectTravelCreatorListById();
    }

    @Test
    @Override
    public void testSelectTravelExpandCreatorListById() {
        super.testSelectTravelExpandCreatorListById();
    }

    @Test
    @Override
    public void testSelectTravelSingleCreatorListById() {
        super.testSelectTravelSingleCreatorListById();
    }

    @Test
    @Override
    public void testSelectTravelListBySingleCreator() {
        super.testSelectTravelListBySingleCreator();
    }

    @Test
    @Override
    public void testSelectTravelListBySingleCreatorVars() {
        super.testSelectTravelListBySingleCreatorVars();
    }
}
