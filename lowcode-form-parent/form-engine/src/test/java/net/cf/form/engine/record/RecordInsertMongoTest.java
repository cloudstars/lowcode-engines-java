package net.cf.form.engine.record;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("mongo")
@RunWith(SpringRunner.class)
public class RecordInsertMongoTest extends RecordInsertTest {

    @Test
    @Override
    public void testInsert() {
        super.testInsert();
    }
}
