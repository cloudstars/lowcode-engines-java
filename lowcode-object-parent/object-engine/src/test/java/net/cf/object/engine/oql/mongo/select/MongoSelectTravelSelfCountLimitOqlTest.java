package net.cf.object.engine.oql.mongo.select;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectTravelSelfCountLimitRepoTest;
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
public class MongoSelectTravelSelfCountLimitOqlTest extends AbstractSelectTravelSelfCountLimitRepoTest {

    @Test
    @Override
    public void testSelectCountOneTravel() {
        super.testSelectCountOneTravel();
    }

    @Test
    @Override
    public void testSelectCountStarTravel() {
        super.testSelectCountStarTravel();
    }

    @Test
    @Override
    public void testSelectTravelWithLimit() {
        super.testSelectTravelWithLimit();
    }

    @Test
    @Override
    public void testSelectTravelWithLimitOffset() {
        super.testSelectTravelWithLimitOffset();
    }

    @Test
    @Override
    public void testSelectCountFieldTravel() {
        super.testSelectCountFieldTravel();
    }
}
