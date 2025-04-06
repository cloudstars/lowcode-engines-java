package io.github.cloudstars.lowcode.bpm.vendor.activiti;

import io.github.cloudstars.lowcode.bpm.vendor.BpmDeployProvider;
import io.github.cloudstars.lowcode.bpm.vendor.BpmProcessProvider;
import io.github.cloudstars.lowcode.bpm.vendor.BpmTaskProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

public class ActivitiBpmVendorAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public BpmDeployProvider deployProvider() {
        return new ActivitiBpmDeployProviderImpl();
    }

    @Bean
    public BpmProcessProvider processProvider() {
        return new ActivitiBpmProcessProviderImpl();
    }

    @Bean
    public BpmTaskProvider taskProvider() {
        return new ActivitiBpmTaskProviderImpl();
    }

}
