package io.github.cloudstars.lowcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BpmCommonsTestConfiguration implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(BpmCommonsTestConfiguration.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

}