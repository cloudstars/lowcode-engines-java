package net.cf.object.engine.oql.mongo.insert.detail;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.insert.detail.AbstractInsertTravelDetailRepoTest;
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
public class MongoInsertTravelDetailOqlTest extends AbstractInsertTravelDetailRepoTest {

    @Test
    @Override
    public void testBatchInsertTravelAndTripVars() {
        super.testBatchInsertTravelAndTripVars();
    }

}
