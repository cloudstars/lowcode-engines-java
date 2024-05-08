package net.cf.object.engine.oql.mongo.select.detail;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.select.detail.AbstractSelectTravelAndTripRepoTest;
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
public class MongoSelectTravelAndTripWhereOqlTest extends AbstractSelectTravelAndTripRepoTest {

    @Test
    @Override
    public void testSelectTravelAndTripByIdAndTripsFromAddr() {
        super.testSelectTravelAndTripByIdAndTripsFromAddr();
    }

}
