package io.github.cloudstars.lowcode.api;

import io.github.cloudstars.lowcode.api.executor.filter.ApiExecuteFilterChain;
import io.github.cloudstars.lowcode.api.executor.filter.ApiExecuteFilterChainImpl;
import io.github.cloudstars.lowcode.api.executor.filter.PathVariableReplaceFilter;
import io.github.cloudstars.lowcode.api.executor.rest.RestTemplateApiInvoker;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ApiExecutorRestAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(this.getClientHttpRequestFactory());
        return restTemplate;
    }

    /**
     * 使用httpclient作为底层客户端
     *
     * @return
     */
    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 50000;

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }


    @Bean
    @ConditionalOnBean(RestTemplate.class)
    @ConditionalOnMissingBean(RestTemplateApiInvoker.class)
    public RestTemplateApiInvoker restTemplateApiInvoker(RestTemplate template) {
        return new RestTemplateApiInvoker(template);
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
