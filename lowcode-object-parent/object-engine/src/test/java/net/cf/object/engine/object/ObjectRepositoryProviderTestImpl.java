package net.cf.object.engine.object;

import net.cf.object.engine.ObjectRepositoryProvider;
import net.cf.object.engine.def.ObjectTestImpl;
import net.cf.form.repository.ObjectRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Profile("mongo")
public class ObjectRepositoryProviderTestImpl implements ObjectRepositoryProvider<ObjectTestImpl> {

    @Resource
    private ObjectRepository repositoryDriver;

    @Override
    public ObjectRepository getByObject(ObjectTestImpl object) {
        return repositoryDriver;
    }
}
