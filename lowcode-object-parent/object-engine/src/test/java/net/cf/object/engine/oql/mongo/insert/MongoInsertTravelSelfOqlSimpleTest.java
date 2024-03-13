package net.cf.object.engine.oql.mongo.insert;

import net.cf.object.engine.ObjectEngineTestApplication;
import net.cf.object.engine.oql.testcase.insert.AbstractInsertTravelSelfRepoSimpleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("mongo")
@SpringBootTest(classes = ObjectEngineTestApplication.class)
public class MongoInsertTravelSelfOqlSimpleTest extends AbstractInsertTravelSelfRepoSimpleTest {

    @Test
    @Override
    public void testInsertTravel() {
        super.testInsertTravel();
    }

}
