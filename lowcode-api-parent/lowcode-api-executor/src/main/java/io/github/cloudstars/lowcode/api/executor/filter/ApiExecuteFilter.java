package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;

/**
 * API执行过滤器
 *
 * @author clouds
 */
public interface ApiExecuteFilter {

    /**
     * 设置过滤链
     *
     * @param filterChain
     */
    // void setFilterChain(ApiExecuteFilterChain filterChain);

    /**
     * 处理请求、响应
     *
     * @param apiRequest API请求
     * @param apiResponse API响应
     */
    void process(ApiRequest apiRequest, ApiResponse apiResponse);

}
