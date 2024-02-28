package net.cf.object.engine.object;

import net.cf.form.repository.ObjectRepository;
import net.cf.object.engine.ObjectRepositoryProvider;
import net.cf.object.engine.def.ObjectTestImpl;

public class ObjectRepositoryProviderTestImpl implements ObjectRepositoryProvider<ObjectTestImpl> {

    private final ObjectRepository repositoryDriver;

    public ObjectRepositoryProviderTestImpl(ObjectRepository repositoryDriver) {
        this.repositoryDriver = repositoryDriver;
    }

    @Override
    public ObjectRepository getByObject(ObjectTestImpl object) {
        return repositoryDriver;
    }
}
