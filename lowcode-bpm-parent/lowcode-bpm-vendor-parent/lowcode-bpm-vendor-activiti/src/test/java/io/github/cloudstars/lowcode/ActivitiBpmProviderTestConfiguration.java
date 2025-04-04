package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmDeployServiceImpl;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessService;
import io.github.cloudstars.lowcode.bpm.engine.service.BpmProcessServiceImpl;
import io.github.cloudstars.lowcode.bpm.engine.vendor.BpmDeployProvider;
import io.github.cloudstars.lowcode.bpm.engine.vendor.BpmProcessProvider;
import io.github.cloudstars.lowcode.bpm.provider.activiti.ActivitiBpmDeployProviderImpl;
import io.github.cloudstars.lowcode.bpm.provider.activiti.ActivitiBpmProcessProviderImpl;
import io.github.cloudstars.lowcode.bpm.provider.activiti.vendor.ActivitiEventListenerImpl;
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
public class ActivitiBpmProviderTestConfiguration extends AbstractBpmEngineTestConfiguration implements ProcessEngineConfigurationConfigurer {

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
    public BpmDeployService deployService(BpmDeployProvider deployProvider) {
        return new BpmDeployServiceImpl(deployProvider);
    }

    @Bean
    public BpmProcessService processEngine(BpmProcessProvider processProvider) {
        return new BpmProcessServiceImpl(processProvider);
    }

}