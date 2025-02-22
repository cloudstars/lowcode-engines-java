package net.cf.object.engine.oql.mongo.select;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.select.AbstractSelectStaffContainsWhereRepoTest;
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
public class MongoSelectStaffContainsWhereTest extends AbstractSelectStaffContainsWhereRepoTest {

    @Test
    @Override
    public void testSelectStaffByHobby() {
        super.testSelectStaffByHobby();
    }

    @Test
    @Override
    public void testSelectStaffByNotHobby() {
        super.testSelectStaffByNotHobby();
    }

}
