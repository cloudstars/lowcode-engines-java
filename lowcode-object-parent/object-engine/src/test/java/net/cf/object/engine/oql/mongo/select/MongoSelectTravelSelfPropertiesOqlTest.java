package net.cf.object.engine.oql.mongo.select;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.select.property.AbstractSelectTravelSelfPropertiesRepoTest;
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
public class MongoSelectTravelSelfPropertiesOqlTest extends AbstractSelectTravelSelfPropertiesRepoTest {

    @Test
    @Override
    public void testSelectTravelCreatorListById() {
        super.testSelectTravelCreatorListById();
    }

    @Test
    @Override
    public void testSelectTravelExpandCreatorListById() {
        super.testSelectTravelExpandCreatorListById();
    }

    @Test
    @Override
    public void testSelectTravelSingleCreatorListById() {
        super.testSelectTravelSingleCreatorListById();
    }

    @Test
    @Override
    public void testSelectTravelListBySingleCreator() {
        super.testSelectTravelListBySingleCreator();
    }

    @Test
    @Override
    public void testSelectTravelListBySingleCreatorVars() {
        super.testSelectTravelListBySingleCreatorVars();
    }
}
