package net.cf.form.repository.mysql.insert;

import net.cf.commons.test.db.dataset.IDataSetOperator;
import net.cf.form.repository.mysql.MySQLObjectRepositoryImpl;
import net.cf.form.repository.mysql.MysqlRepositoryTestApplication;
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
    private MySQLObjectRepositoryImpl repository;

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
