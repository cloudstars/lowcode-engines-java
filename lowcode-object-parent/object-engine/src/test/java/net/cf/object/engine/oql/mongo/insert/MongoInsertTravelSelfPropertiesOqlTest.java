package net.cf.object.engine.oql.mongo.insert;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.insert.AbstractInsertTravelSelfPropertiesRepoTest;
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
public class MongoInsertTravelSelfPropertiesOqlTest extends AbstractInsertTravelSelfPropertiesRepoTest {

    @Test
    @Override
    public void testInsertTravelWithCreatorVars() {
        super.testInsertTravelWithCreatorVars();
    }

    @Test
    @Override
    public void testInsertTravelWithCreator() {
        super.testInsertTravelWithCreator();
    }

    @Test
    @Override
    public void testInsertTravelWithAttachesVars() {
        super.testInsertTravelWithAttachesVars();
    }

}
