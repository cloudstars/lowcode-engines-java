package net.cf.object.engine.oql.mongo.select.lookup;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.select.lookup.AbstractSelectStaffLookupHobbyMultiRefRepoTest;
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
public class MongoSelectStaffLookupHobbyMultiRefOqlTest extends AbstractSelectStaffLookupHobbyMultiRefRepoTest {

    @Test
    @Override
    public void testSelectStaffLookupHobby() {
        super.testSelectStaffLookupHobby();
    }

}
