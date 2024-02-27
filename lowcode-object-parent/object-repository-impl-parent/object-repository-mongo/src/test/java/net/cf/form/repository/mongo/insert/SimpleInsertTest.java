package net.cf.form.repository.mongo.insert;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.mongo.MongoObjectRepositoryImpl;
import net.cf.form.repository.mongo.MongoRepositoryTestApplication;
import net.cf.commons.test.dataset.IDataSetOperator;
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

    public SimpleInsertTest(ObjectRepository repository, IDataSetOperator dataSetOperator) {
        super(repository, dataSetOperator);
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
