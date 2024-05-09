
package net.cf.object.engine.oql.mongo.select;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectExpenseAggregateRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("mongo")
@Import(value = {ObjectEngineOqlMongoTestApplication.class})
@SpringBootTest(classes = ObjectEngineOqlMongoTestApplication.class)
public class MongoSelectExpenseAggregateOqlTest extends AbstractSelectExpenseAggregateRepoTest {

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
