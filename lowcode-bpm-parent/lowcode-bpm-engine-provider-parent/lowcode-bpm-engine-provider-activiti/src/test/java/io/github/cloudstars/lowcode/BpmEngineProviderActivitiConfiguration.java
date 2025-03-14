package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.engine.provider.EngineProvider;
import io.github.cloudstars.lowcode.bpm.engine.provider.activiti.ActivitiBpmProviderImpl;
import net.cf.bpm.engine.ProcessEngine;
import net.cf.bpm.engine.ProcessEngineImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BpmEngineProviderActivitiConfiguration {

    @Bean
    public EngineProvider engineProvider() {
        return new ActivitiBpmProviderImpl();
    }

    @Bean
    public ProcessEngine processEngine(EngineProvider engineProvider) {
        return new ProcessEngineImpl(engineProvider);
    }

}