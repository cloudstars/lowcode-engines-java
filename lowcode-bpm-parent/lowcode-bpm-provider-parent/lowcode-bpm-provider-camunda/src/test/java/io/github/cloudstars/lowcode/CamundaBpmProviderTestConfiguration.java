package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.engine.BpmProcessEngine;
import io.github.cloudstars.lowcode.bpm.engine.BpmProcessEngineImpl;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmProvider;
import io.github.cloudstars.lowcode.bpm.provider.camunda.CamundaBpmProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaBpmProviderTestConfiguration extends AbstractBpmEngineTestConfiguration {

    @Bean
    public BpmProvider bpmEngineProvider() {
        return new CamundaBpmProviderImpl();
    }

    @Bean
    public BpmProcessEngine bpmProcessEngine(BpmProvider bpmProvider) {
        return new BpmProcessEngineImpl(bpmProvider);
    }

}