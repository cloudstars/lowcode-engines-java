package net.cf.object.engine.oql.mongo.select;

import net.cf.object.engine.ObjectEngineTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectTravelSelfSimpleRepoTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("mongo")
@SpringBootTest(classes = ObjectEngineTestApplication.class)
public class MongoSelectTravelSelfSimpleOqlTest extends AbstractSelectTravelSelfSimpleRepoTest {

    @Test
    @Override
    public void testSelectTravelList() {
        super.testSelectTravelList();
    }
}
