package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.engine.BpmProcessEngine;
import io.github.cloudstars.lowcode.bpm.engine.BpmProcessEngineImpl;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmProvider;
import io.github.cloudstars.lowcode.bpm.provider.activiti.ActivitiBpmProviderImpl;
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
    public BpmProvider bpmProvider() {
        return new ActivitiBpmProviderImpl();
    }

    @Bean
    public BpmProcessEngine bpmProcessEngine(BpmProvider bpmProvider) {
        return new BpmProcessEngineImpl(bpmProvider);
    }

}