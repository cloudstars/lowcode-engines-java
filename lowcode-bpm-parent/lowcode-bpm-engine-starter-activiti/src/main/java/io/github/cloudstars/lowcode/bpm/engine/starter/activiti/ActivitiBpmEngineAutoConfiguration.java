package io.github.cloudstars.lowcode.bpm.engine.starter.activiti;

import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployServiceImpl;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessServiceImpl;
import io.github.cloudstars.lowcode.bpm.engine.service.task.BpmTaskService;
import io.github.cloudstars.lowcode.bpm.engine.service.task.BpmTaskServiceImpl;
import io.github.cloudstars.lowcode.bpm.vendor.BpmDeployProvider;
import io.github.cloudstars.lowcode.bpm.vendor.BpmProcessProvider;
import io.github.cloudstars.lowcode.bpm.vendor.BpmTaskProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

public class ActivitiBpmEngineAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public BpmDeployService bpmDeployService(BpmDeployProvider deployProvider) {
        return new BpmDeployServiceImpl(deployProvider);
    }

    @Bean
    public BpmProcessService bpmProcessService(BpmProcessProvider processProvider) {
        return new BpmProcessServiceImpl(processProvider);
    }

    @Bean
    public BpmTaskService bpmTaskService(BpmTaskProvider taskProvider) {
        return new BpmTaskServiceImpl(taskProvider);
    }

}
