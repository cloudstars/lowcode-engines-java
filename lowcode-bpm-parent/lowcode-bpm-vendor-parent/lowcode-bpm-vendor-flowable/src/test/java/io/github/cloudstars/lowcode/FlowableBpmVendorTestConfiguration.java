package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployServiceImpl;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessServiceImpl;
import io.github.cloudstars.lowcode.bpm.vendor.BpmDeployProvider;
import io.github.cloudstars.lowcode.bpm.vendor.BpmProcessProvider;
import io.github.cloudstars.lowcode.bpm.vendor.flowable.FlowableBpmDeployProviderImpl;
import io.github.cloudstars.lowcode.bpm.vendor.flowable.FlowableBpmProcessProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowableBpmVendorTestConfiguration extends AbstractBpmEngineTestConfiguration {

    @Bean
    public BpmDeployProvider deployProvider() {
        return new FlowableBpmDeployProviderImpl();
    }

    @Bean
    public BpmProcessProvider processProvider() {
        return new FlowableBpmProcessProviderImpl();
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