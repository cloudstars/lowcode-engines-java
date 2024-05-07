package net.cf.object.engine.oql.mysql.insert;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.insert.AbstractInsertTravelSelfPropertiesRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlInsertTravelSelfPropertiesOqlTest extends AbstractInsertTravelSelfPropertiesRepoTest {

    @Test
    @Override
    public void testInsertTravelWithCreator() {
        super.testInsertTravelWithCreator();
    }

    @Test
    @Override
    public void testInsertTravelWithCreatorVars() {
        super.testInsertTravelWithCreatorVars();
    }

    @Test
    @Override
    public void testInsertTravelWithAttachesVars() {
        super.testInsertTravelWithAttachesVars();
    }

}
