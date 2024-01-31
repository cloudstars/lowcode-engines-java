package net.cf.form.engine.oql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("mongo")
@RunWith(SpringRunner.class)
public class OqlInsertMongoTest extends OqlInsertTest {

    @Test
    @Override
    public void testInsertTravelApply() {
        super.testInsertTravelApply();
    }
}
