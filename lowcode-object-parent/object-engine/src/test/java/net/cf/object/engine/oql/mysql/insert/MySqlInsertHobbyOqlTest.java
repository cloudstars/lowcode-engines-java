
package net.cf.object.engine.oql.mysql.insert;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.insert.AbstractInsertHobbyRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlInsertHobbyOqlTest extends AbstractInsertHobbyRepoTest {

    @Test
    @Override
    public void testInsertHobby() {
        super.testInsertHobby();
    }

    @Test
    @Override
    public void testInsertHobbyVars() {
        super.testInsertHobbyVars();
    }

}
