package net.cf.object.engine.oql.testcase.update;

import net.cf.object.engine.oql.testcase.AbstractOqlTest;
import net.cf.object.engine.oql.testcase.ObjectEngineStatementTestApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineStatementTestApplication.class)
public class UpdateStaffWithColumnStmtTest extends AbstractOqlTest implements UpdateStaffWithColumnTest {

    public UpdateStaffWithColumnStmtTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testUpdateStaffModifierWithCreator() {

    }

    @Override
    public void testUpdateStaffModifierWithCreatorVars() {

    }
}
