package net.cf.object.engine.oql.mysql.update;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.update.AbstractUpdateTravelSelfPropertiesRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlUpdateTravelSelfPropertiesOqlTest extends AbstractUpdateTravelSelfPropertiesRepoTest {

    @Test
    @Override
    public void testUpdateTravelModifierById() {
        super.testUpdateTravelModifierById();
    }

    @Test
    @Override
    public void testUpdateTravelModifierByIdVars() {
        super.testUpdateTravelModifierByIdVars();
    }

    @Test
    @Override
    public void testUpdateTravelWithAttachesByIdVars() {
        super.testUpdateTravelWithAttachesByIdVars();
    }

}
