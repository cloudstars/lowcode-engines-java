package net.cf.object.engine.oql.testcase;

import net.cf.form.repository.ObjectRepository;

import javax.annotation.Resource;

public abstract class AbstractOqlRepoTest extends AbstractOqlTest {

    @Resource
    protected ObjectRepository repository;

    public AbstractOqlRepoTest(String oqlFilePath) {
        super(oqlFilePath);
    }
}
