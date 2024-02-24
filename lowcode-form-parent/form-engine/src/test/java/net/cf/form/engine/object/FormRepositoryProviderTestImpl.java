package net.cf.form.engine.object;

import net.cf.form.engine.FormRepositoryProvider;
import net.cf.form.repository.FormRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Profile("mongo")
public class FormRepositoryProviderTestImpl implements FormRepositoryProvider<XObjectTestImpl> {

    @Resource
    private FormRepository repositoryDriver;

    @Override
    public FormRepository getByObject(XObjectTestImpl object) {
        return repositoryDriver;
    }
}
