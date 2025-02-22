package net.cf.object.engine.oql.mysql.select;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectStaffContainsWhereRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlSelectStaffContainsWhereTest extends AbstractSelectStaffContainsWhereRepoTest {

    @Test
    @Override
    public void testSelectStaffByHobby() {
        super.testSelectStaffByHobby();
    }

    @Test
    @Override
    public void testSelectStaffByNotHobby() {
        super.testSelectStaffByNotHobby();
    }

}
