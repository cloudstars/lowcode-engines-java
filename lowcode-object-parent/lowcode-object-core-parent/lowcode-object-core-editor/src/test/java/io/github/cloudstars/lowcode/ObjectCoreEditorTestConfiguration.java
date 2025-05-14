package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.object.core.editor.Spec1ObjectConfigParser;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectCoreEditorTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    Spec1ObjectConfigParser spec1ObjectConfigParser() {
        return new Spec1ObjectConfigParser();
    }

}