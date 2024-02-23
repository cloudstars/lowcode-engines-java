package net.cf.form.repository.testcases.statement;

import net.cf.form.repository.FormRepository;

public abstract class AbstractTestCase {

    protected final FormRepository repository;

    protected final InitDataLoader initDataLoader;

    protected AbstractTestCase(FormRepository repository) {
        this.repository = repository;
        this.initDataLoader = null;
    }

    public AbstractTestCase(FormRepository repository, InitDataLoader initDataLoader) {
        this.repository = repository;
        this.initDataLoader = initDataLoader;
    }
}
