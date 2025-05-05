package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.commons.test.ObjectConfigResolverTestImpl;
import io.github.cloudstars.lowcode.object.core.editor.XObjectConfigResolver;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

public class ObjectCommonsTestAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public XObjectConfigResolver objectConfigResolver() {
        return new ObjectConfigResolverTestImpl();
    }

}
