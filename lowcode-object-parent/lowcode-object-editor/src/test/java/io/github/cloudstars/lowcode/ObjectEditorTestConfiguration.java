package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.object.editor.spec1.Spec1ObjectConfigParser;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectEditorTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    Spec1ObjectConfigParser spec1ObjectConfigParser() {
        return new Spec1ObjectConfigParser();
    }

}