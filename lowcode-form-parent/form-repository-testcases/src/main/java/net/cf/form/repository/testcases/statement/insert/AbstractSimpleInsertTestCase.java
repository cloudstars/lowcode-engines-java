package net.cf.form.repository.testcases.statement.insert;

import net.cf.form.repository.FormRepository;
import net.cf.form.repository.testcases.statement.InitDataLoader;

/**
 * 简单插入语句的测试
 *
 * @author clouds
 */
public abstract class AbstractSimpleInsertTestCase {

    protected final FormRepository repository;

    protected final InitDataLoader initDataLoader;

    protected AbstractSimpleInsertTestCase(FormRepository repository, InitDataLoader initDataLoader) {
        this.repository = repository;
        this.initDataLoader = initDataLoader;
    }

    protected void test1() {}

    protected void test2() {}

}
