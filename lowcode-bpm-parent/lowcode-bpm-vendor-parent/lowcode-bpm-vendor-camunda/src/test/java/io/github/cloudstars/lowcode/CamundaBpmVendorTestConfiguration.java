package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployServiceImpl;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessServiceImpl;
import io.github.cloudstars.lowcode.bpm.vendor.BpmDeployProvider;
import io.github.cloudstars.lowcode.bpm.vendor.BpmProcessProvider;
import io.github.cloudstars.lowcode.bpm.vendor.camunda.CamundaBpmDeployProviderImpl;
import io.github.cloudstars.lowcode.bpm.vendor.camunda.CamundaBpmProcessProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaBpmVendorTestConfiguration extends AbstractBpmEngineTestConfiguration {

    @Bean
    public BpmDeployProvider deployProvider() {
        return new CamundaBpmDeployProviderImpl();
    }

    @Bean
    public BpmProcessProvider processProvider() {
        return new CamundaBpmProcessProviderImpl();
    }

    @Bean
    public BpmDeployService deployService(BpmDeployProvider deployProvider) {
        return new BpmDeployServiceImpl(deployProvider);
    }

    @Bean
    public BpmProcessService processService(BpmProcessProvider processProvider) {
        return new BpmProcessServiceImpl(processProvider);
    }

}