package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.api.template.OptionListApiConfigTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsApiTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public OptionListApiConfigTemplate optionListApiConfigTemplate() {
        return new OptionListApiConfigTemplate();
    }

}