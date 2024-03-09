package net.cf.form.repository.mongo.insert;

import net.cf.commons.test.db.dataset.IDataSetOperator;
import net.cf.form.repository.mongo.MongoObjectRepositoryImpl;
import net.cf.form.repository.mongo.MongoRepositoryTestApplication;
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
    private MongoObjectRepositoryImpl repository;

    @Resource
    private IDataSetOperator dataSetOperator;

    public SimpleInsertTest() {
        super(null, null);
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
