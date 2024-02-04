package net.cf.form.engine.object;

import net.cf.form.engine.FormRepositoryResolver;
import net.cf.form.repository.FormRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Profile("mongo")
public class FormRepositoryResolverTestImpl implements FormRepositoryResolver {

    @Resource
    private FormRepository repositoryDriver;
    
    @Override
    public FormRepository resolveRepository(XObject object) {
        return repositoryDriver;
    }
}
