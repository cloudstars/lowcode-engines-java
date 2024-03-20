package net.cf.object.engine.oql.mongo.update;

import net.cf.object.engine.ObjectEngineTestApplication;
import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.update.AbstractUpdateTravelSelfSimpleRepoTest;
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
public class MongoUpdateTravelSelfSimpleOqlTest extends AbstractUpdateTravelSelfSimpleRepoTest {

    @Test
    @Override
    public void testUpdateTravelById() {
        super.testUpdateTravelById();
    }


    @Test
    @Override
    public void testUpdateTravelByIdVars() {
        super.testUpdateTravelByIdVars();
    }

}
