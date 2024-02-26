package net.cf.form.engine.object;

import net.cf.form.engine.ObjectRepositoryProvider;
import net.cf.form.engine.def.XObjectTestImpl;
import net.cf.form.repository.ObjectRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Profile("mongo")
public class ObjectRepositoryProviderTestImpl implements ObjectRepositoryProvider<XObjectTestImpl> {

    @Resource
    private ObjectRepository repositoryDriver;

    @Override
    public ObjectRepository getByObject(XObjectTestImpl object) {
        return repositoryDriver;
    }
}
