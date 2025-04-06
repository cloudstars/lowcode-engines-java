package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployServiceImpl;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessServiceImpl;
import io.github.cloudstars.lowcode.bpm.engine.service.task.BpmTaskService;
import io.github.cloudstars.lowcode.bpm.engine.service.task.BpmTaskServiceImpl;
import io.github.cloudstars.lowcode.bpm.vendor.BpmDeployProvider;
import io.github.cloudstars.lowcode.bpm.vendor.BpmProcessProvider;
import io.github.cloudstars.lowcode.bpm.vendor.BpmTaskProvider;
import io.github.cloudstars.lowcode.bpm.vendor.activiti.ActivitiBpmDeployProviderImpl;
import io.github.cloudstars.lowcode.bpm.vendor.activiti.ActivitiBpmProcessProviderImpl;
import io.github.cloudstars.lowcode.bpm.vendor.activiti.ActivitiBpmTaskProviderImpl;
import io.github.cloudstars.lowcode.bpm.vendor.activiti.ActivitiEventListenerImpl;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ActivitiBpmVendorTestConfiguration extends AbstractBpmEngineTestConfiguration implements ProcessEngineConfigurationConfigurer {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        // 注册一个监听器
        Map<String, List<ActivitiEventListener>> actiEventListenerMap = new HashMap<>();
        actiEventListenerMap.put(ActivitiEventType.PROCESS_COMPLETED.name(), Arrays.asList(new ActivitiEventListenerImpl()));
        springProcessEngineConfiguration.setTypedEventListeners(actiEventListenerMap);
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