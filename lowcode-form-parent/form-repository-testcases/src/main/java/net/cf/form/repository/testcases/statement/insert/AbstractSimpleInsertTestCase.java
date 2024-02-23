package net.cf.form.repository.testcases.statement.insert;

import net.cf.form.repository.FormRepository;
import net.cf.form.repository.testcases.statement.AbstractTestCase;
import net.cf.form.repository.testcases.statement.InitDataLoader;

/**
 * 简单插入语句的测试
 *
 * @author clouds
 */
public abstract class AbstractSimpleInsertTestCase extends AbstractTestCase {

    protected AbstractSimpleInsertTestCase(FormRepository repository, InitDataLoader initDataLoader) {
        super(repository, initDataLoader);
    }

    protected void test1() {
    }

    protected void test2() {}

}
