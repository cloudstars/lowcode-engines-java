package net.cf.form.repository.mysql.insert;

import net.cf.form.repository.FormRepository;
import net.cf.form.repository.mysql.MySQLFormRepositoryImpl;
import net.cf.form.repository.mysql.MysqlRepositoryTestApplication;
import net.cf.form.repository.testcases.statement.InitDataLoader;
import net.cf.form.repository.testcases.statement.insert.AbstractSimpleInsertTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = MysqlRepositoryTestApplication.class)
public class SimpleInsertTest extends AbstractSimpleInsertTestCase {

    @Resource
    private MySQLFormRepositoryImpl repository;

    @Resource
    private InitDataLoader initDataLoader;

    public SimpleInsertTest(FormRepository repository, InitDataLoader initDataLoader) {
        super(repository, initDataLoader);
    }

    @Test
    @Override
    protected void test1() {
        super.test1();
    }

    @Test
    @Override
    protected void test2() {
        super.test2();
    }
}
