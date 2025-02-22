package net.cf.object.engine.oql.mongo.update.detail;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.update.detail.AbstractUpdateTravelDetailRepoTest;
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
public class MongoUpdateTravelDetailOqlTest extends AbstractUpdateTravelDetailRepoTest {

    @Test
    @Override
    public void testUpdateTravelAndTripByIdVars() {
        super.testUpdateTravelAndTripByIdVars();
    }

    @Test
    @Override
    public void testUpdateTravelAndTripById() {
        super.testUpdateTravelAndTripById();
    }

    @Test
    @Override
    public void testUpdateTravelAndTripByIdWithNull() {
        super.testUpdateTravelAndTripByIdWithNull();
    }

    @Test
    @Override
    public void testUpdateTravelAndTripByIdWithNullVars() {
        super.testUpdateTravelAndTripByIdWithNullVars();
    }

    @Test
    @Override
    public void testUpdateTravelAndTripByIdWithEmpty() {
        super.testUpdateTravelAndTripByIdWithEmpty();
    }

    @Test
    @Override
    public void testUpdateTravelAndTripByIdWithEmptyVars() {
        super.testUpdateTravelAndTripByIdWithEmptyVars();
    }

}
