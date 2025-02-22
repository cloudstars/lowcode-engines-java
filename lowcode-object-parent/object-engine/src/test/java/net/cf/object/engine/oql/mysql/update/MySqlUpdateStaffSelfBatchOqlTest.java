
package net.cf.object.engine.oql.mysql.update;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.update.AbstractUpdateStaffSelfBatchRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlUpdateStaffSelfBatchOqlTest extends AbstractUpdateStaffSelfBatchRepoTest {

    @Test
    @Override
    public void testUpdateStaffBatchVars() {
        super.testUpdateStaffBatchVars();
    }

}
