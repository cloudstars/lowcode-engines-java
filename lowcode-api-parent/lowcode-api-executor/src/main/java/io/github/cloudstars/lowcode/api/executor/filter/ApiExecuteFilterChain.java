package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.api.executor.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.ApiResponse;

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
     * @return
     */
    ApiExecuteFilterChain doFilter(ApiRequest apiRequest, ApiResponse apiResponse);

}
