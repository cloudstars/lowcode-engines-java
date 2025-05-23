package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.component.commons.AbcComponent;
import io.github.cloudstars.lowcode.component.commons.ComponentConfigClassFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentCommonsTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ComponentConfigClassFactory.register(AbcComponent.class);
    }

}