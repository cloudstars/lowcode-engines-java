package net.cf.form.engine.object;

import net.cf.form.engine.FormEngineRepositoryResolver;
import net.cf.form.engine.repository.FormEngineRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Profile("mongo")
public class FormEngineRepositoryResolverTestImpl implements FormEngineRepositoryResolver {

    @Resource
    private FormEngineRepository repositoryDriver;
    
    @Override
    public FormEngineRepository resolveRepository(XObject object) {
        return repositoryDriver;
    }
}
