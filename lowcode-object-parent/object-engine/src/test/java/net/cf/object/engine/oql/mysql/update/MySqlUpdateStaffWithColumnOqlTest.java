package net.cf.object.engine.oql.mysql.update;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.update.AbstractUpdateStaffWithColumnRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlUpdateStaffWithColumnOqlTest extends AbstractUpdateStaffWithColumnRepoTest {

    @Test
    @Override
    public void testUpdateStaffModifierWithCreator() {
        super.testUpdateStaffModifierWithCreator();
    }

    @Test
    @Override
    public void testUpdateStaffModifierWithCreatorVars() {
        super.testUpdateStaffModifierWithCreatorVars();
    }

    @Test
    @Override
    public void testUpdateStaffDescrWithNameRela() {
        super.testUpdateStaffDescrWithNameRela();
    }

    @Test
    @Override
    public void testUpdateStaffDescrWithNameRelaVars() {
        super.testUpdateStaffDescrWithNameRelaVars();
    }

    @Test
    @Override
    public void testUpdateStaffAgeByIdVars() {
        super.testUpdateStaffAgeByIdVars();
    }
}
