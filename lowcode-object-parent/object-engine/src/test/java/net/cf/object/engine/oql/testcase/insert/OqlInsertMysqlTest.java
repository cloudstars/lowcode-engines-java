package net.cf.object.engine.oql.testcase.insert;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("mysql")
@RunWith(SpringRunner.class)
public class OqlInsertMysqlTest extends OqlInsertTest {
}
