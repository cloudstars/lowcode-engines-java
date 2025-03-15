package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.engine.BpmProcessEngine;
import io.github.cloudstars.lowcode.bpm.engine.BpmProcessEngineImpl;
import io.github.cloudstars.lowcode.bpm.engine.provider.BpmEngineProvider;
import io.github.cloudstars.lowcode.bpm.engine.provider.activiti.ActivitiBpmEngineProviderImpl;
import io.github.cloudstars.lowcode.bpm.engine.provider.activiti.ActivitiEventListenerImpl;
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
public class ActivitiBpmEngineProviderTestConfiguration extends AbstractBpmEngineTestConfiguration implements ProcessEngineConfigurationConfigurer {

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        // 注册一个监听器
        Map<String, List<ActivitiEventListener>> actiEventListenerMap = new HashMap<>();
        actiEventListenerMap.put(ActivitiEventType.PROCESS_COMPLETED.name(), Arrays.asList(new ActivitiEventListenerImpl()));
        springProcessEngineConfiguration.setTypedEventListeners(actiEventListenerMap);
    }

    @Bean
    public BpmEngineProvider bpmEngineProvider() {
        return new ActivitiBpmEngineProviderImpl();
    }

    @Bean
    public BpmProcessEngine bpmProcessEngine(BpmEngineProvider bpmEngineProvider) {
        return new BpmProcessEngineImpl(bpmEngineProvider);
    }

}