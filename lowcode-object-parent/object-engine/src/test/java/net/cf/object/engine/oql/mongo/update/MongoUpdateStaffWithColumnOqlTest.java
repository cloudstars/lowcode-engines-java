package net.cf.object.engine.oql.mongo.update;

import net.cf.object.engine.oql.mongo.ObjectEngineOqlMongoTestApplication;
import net.cf.object.engine.oql.testcase.update.AbstractUpdateStaffWithColumnRepoTest;
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
public class MongoUpdateStaffWithColumnOqlTest extends AbstractUpdateStaffWithColumnRepoTest {

    @Test
    @Override
    public void testUpdateStaffModifierWithCreator() {
        super.testUpdateStaffModifierWithCreator();
    }

    @Test
    @Override
    public void testUpdateStaffModifierWithCreatorVars() {
        super.testUpdateStaffModifierWithCreatorVars();
    }

    @Test
    @Override
    public void testUpdateStaffDescrWithNameRela() {
        super.testUpdateStaffDescrWithNameRela();
    }

    @Test
    @Override
    public void testUpdateStaffDescrWithNameRelaVars() {
        super.testUpdateStaffDescrWithNameRelaVars();
    }

    @Test
    @Override
    public void testUpdateStaffAgeByIdVars() {
        super.testUpdateStaffAgeByIdVars();
    }

}
