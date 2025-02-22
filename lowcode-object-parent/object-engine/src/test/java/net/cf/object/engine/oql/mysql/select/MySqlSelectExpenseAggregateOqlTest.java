
package net.cf.object.engine.oql.mysql.select;

import net.cf.object.engine.oql.mysql.ObjectEngineOqlMySqlTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectExpenseAggregateRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEngineOqlMySqlTestApplication.class)
public class MySqlSelectExpenseAggregateOqlTest extends AbstractSelectExpenseAggregateRepoTest {

    @Test
    @Override
    public void testSelectExpenseCount() {
        super.testSelectExpenseCount();
    }

    @Test
    @Override
    public void testSelectExpenseSum() {
        super.testSelectExpenseSum();
    }

    @Test
    @Override
    public void testSelectExpenseAvg() {
        super.testSelectExpenseAvg();
    }

    @Test
    @Override
    public void testSelectExpenseMax() {
        super.testSelectExpenseMax();
    }

    @Test
    @Override
    public void testSelectExpenseMin() {
        super.testSelectExpenseMin();
    }
}
