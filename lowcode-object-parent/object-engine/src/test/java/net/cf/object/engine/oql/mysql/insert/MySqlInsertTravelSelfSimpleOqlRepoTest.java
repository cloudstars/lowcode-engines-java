package net.cf.object.engine.oql.mysql.insert;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.insert.AbstractInsertTravelSelfSimpleRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlInsertTravelSelfSimpleOqlRepoTest extends AbstractInsertTravelSelfSimpleRepoTest {

    @Test
    @Override
    public void testInsertTravel() {
        super.testInsertTravel();
    }

    @Test
    @Override
    public void testInsertTravelVars() {
        super.testInsertTravelVars();
    }

}
