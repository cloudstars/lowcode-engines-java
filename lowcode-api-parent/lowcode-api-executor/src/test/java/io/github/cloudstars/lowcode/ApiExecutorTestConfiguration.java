package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.api.executor.filter.ApiExecuteFilterChain;
import io.github.cloudstars.lowcode.api.executor.filter.ApiExecuteFilterChainImpl;
import io.github.cloudstars.lowcode.api.executor.filter.PathVariableReplaceFilter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiExecutorTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    public ApiExecuteFilterChain apiExecuteFilterChain() {
        return new ApiExecuteFilterChainImpl();
    }

    @Bean
    public PathVariableReplaceFilter pathVariableReplaceFilter(ApiExecuteFilterChain filterChain) {
        PathVariableReplaceFilter filter = new PathVariableReplaceFilter(filterChain);
        filterChain.addFilter(filter);
        return filter;
    }
}