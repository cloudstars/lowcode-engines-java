package net.cf.object.engine.oql.mysql.select.lookup;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.select.lookup.AbstractSelectLookupOnlyOneRefRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlSelectLookupOnlyOneRefOqlTest extends AbstractSelectLookupOnlyOneRefRepoTest {

    @Test
    @Override
    public void testSelectAllFields() {
        super.testSelectAllFields();
    }

    @Test
    @Override
    public void testSelectMultiFields() {
        super.testSelectMultiFields();
    }

    @Test
    @Override
    public void testSelectExpandFields() {
        super.testSelectExpandFields();
    }
}