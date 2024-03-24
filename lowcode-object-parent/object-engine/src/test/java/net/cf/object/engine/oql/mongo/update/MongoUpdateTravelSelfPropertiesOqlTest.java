package net.cf.object.engine.oql.mongo.update;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.update.AbstractUpdateTravelSelfPropertiesRepoTest;
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
public class MongoUpdateTravelSelfPropertiesOqlTest extends AbstractUpdateTravelSelfPropertiesRepoTest {

    @Test
    @Override
    public void testUpdateTravelModifierById() {
        super.testUpdateTravelModifierById();
    }

    @Test
    @Override
    public void testUpdateTravelModifierByIdVars() {
        super.testUpdateTravelModifierByIdVars();
    }

    @Test
    @Override
    public void testUpdateTravelExpandModifierById() {
        super.testUpdateTravelExpandModifierById();
    }

    @Test
    @Override
    public void testUpdateTravelExpandModifierByIdVars() {
        super.testUpdateTravelExpandModifierByIdVars();
    }

    @Test
    @Override
    public void testUpdateTravelSingleModifierById() {
        super.testUpdateTravelSingleModifierById();
    }

    @Test
    @Override
    public void testUpdateTravelSingleModifierByIdVars() {
        super.testUpdateTravelSingleModifierByIdVars();
    }
}
