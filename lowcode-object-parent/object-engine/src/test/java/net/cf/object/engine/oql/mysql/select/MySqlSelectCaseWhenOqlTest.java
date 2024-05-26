package net.cf.object.engine.oql.mysql.select;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectWhereIsNullRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlSelectCaseWhenOqlTest extends AbstractSelectWhereIsNullRepoTest {

    @Test
    @Override
    public void testSelectWhereIsNull() {
        super.testSelectWhereIsNull();
    }

    @Test
    @Override
    public void testSelectWhereIsNotNull() {
        super.testSelectWhereIsNotNull();
    }

}
