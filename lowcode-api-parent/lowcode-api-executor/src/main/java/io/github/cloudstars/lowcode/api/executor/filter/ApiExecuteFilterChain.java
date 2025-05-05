package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;

/**
 * API过滤链
 *
 * @author clouds
 */
public interface ApiExecuteFilterChain {

    /**
     * 执行过滤器
     *
     * @param apiRequest
     * @param apiResponse
     */
    void doFilter(ApiRequest apiRequest, ApiResponse apiResponse);

    /**
     * 添加一个过滤器
     *
     * @param filter 过滤器
     */
    void addFilter(ApiExecuteFilter filter);

}
