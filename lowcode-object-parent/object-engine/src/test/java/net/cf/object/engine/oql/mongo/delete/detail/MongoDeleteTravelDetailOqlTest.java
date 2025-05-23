package net.cf.object.engine.oql.mongo.delete.detail;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.delete.detail.AbstractDeleteTravelDetailRepoTest;
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
public class MongoDeleteTravelDetailOqlTest extends AbstractDeleteTravelDetailRepoTest {

    @Test
    @Override
    public void testDeleteTravelAndTripById() {
        super.testDeleteTravelAndTripById();
    }

    @Test
    @Override
    public void testDeleteTravelAndTripByIdVars() {
        super.testDeleteTravelAndTripByIdVars();
    }

    @Test
    @Override
    public void testDeleteTravelAndTripInIds() {
        super.testDeleteTravelAndTripInIds();
    }

    @Test
    @Override
    public void testDeleteTravelAndTripInIdsVars() {
        super.testDeleteTravelAndTripInIdsVars();
    }

}
