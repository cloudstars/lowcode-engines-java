package io.github.cloudstars.lowcode.api.executor.filter;

import io.github.cloudstars.lowcode.api.executor.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.ApiResponse;

/**
 * API执行过滤器
 *
 * @author clouds
 */
public interface ApiExecuteFilter {

    /**
     * 处理请求、响应
     *
     * @param apiRequest API请求
     * @param apiResponse API响应
     */
    void process(ApiRequest apiRequest, ApiResponse apiResponse);

}
