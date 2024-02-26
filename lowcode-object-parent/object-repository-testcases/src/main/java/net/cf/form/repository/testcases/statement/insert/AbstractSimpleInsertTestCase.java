package net.cf.form.repository.testcases.statement.insert;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.testcases.statement.AbstractTestCase;
import net.cf.commons.test.dataset.IDataSetOperator;

/**
 * 简单插入语句的测试
 *
 * @author clouds
 */
public abstract class AbstractSimpleInsertTestCase extends AbstractTestCase {

    protected AbstractSimpleInsertTestCase(ObjectRepository repository, IDataSetOperator dataSetOperator) {
        super(repository, dataSetOperator);
    }

    protected void test1() {
    }

    protected void test2() {}

}
