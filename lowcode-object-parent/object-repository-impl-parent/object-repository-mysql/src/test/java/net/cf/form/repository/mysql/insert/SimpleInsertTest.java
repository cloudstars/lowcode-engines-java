package net.cf.form.repository.mysql.insert;

import net.cf.form.repository.mysql.MySqlRepositoryTestApplication;
import net.cf.form.repository.testcases.statement.insert.AbstractSimpleInsertTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySqlRepositoryTestApplication.class)
public class SimpleInsertTest extends AbstractSimpleInsertTestCase {

    public SimpleInsertTest() {
    }

    @Test
    @Override
    public void test1() {
        super.test1();
    }

    @Test
    @Override
    public void test2() {
        super.test2();
    }


}
