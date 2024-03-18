package net.cf.object.engine.oql.mysql.update;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.update.AbstractUpdateTravelSelfPropertiesRepoTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlUpdateTravelSelfPropertiesOqlTest extends AbstractUpdateTravelSelfPropertiesRepoTest {

    @Override
    public void testUpdateTravelCreatorById() {
        super.testUpdateTravelCreatorById();
    }

    @Override
    public void testUpdateTravelCreatorByIdVars() {
        super.testUpdateTravelCreatorByIdVars();
    }

    @Override
    public void testUpdateTravelExpandCreatorById() {
        super.testUpdateTravelExpandCreatorById();
    }

    @Override
    public void testUpdateTravelExpandCreatorByIdVars() {
        super.testUpdateTravelExpandCreatorByIdVars();
    }

    @Override
    public void testUpdateTravelSingleCreatorById() {
        super.testUpdateTravelSingleCreatorById();
    }

    @Override
    public void testUpdateTravelSingleCreatorByIdVars() {
        super.testUpdateTravelSingleCreatorByIdVars();
    }
}
