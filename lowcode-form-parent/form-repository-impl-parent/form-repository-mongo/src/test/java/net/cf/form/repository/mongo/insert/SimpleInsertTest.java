package net.cf.form.repository.mongo.insert;

import net.cf.form.repository.FormRepository;
import net.cf.form.repository.mongo.MongoFormRepositoryImpl;
import net.cf.form.repository.mongo.MongoRepositoryTestApplication;
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
@SpringBootTest(classes = MongoRepositoryTestApplication.class)
public class SimpleInsertTest extends AbstractSimpleInsertTestCase {

    @Resource
    private MongoFormRepositoryImpl repository;

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
