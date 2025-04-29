package io.github.cloudstars.lowcode.api.executor;

import io.github.cloudstars.lowcode.api.executor.filter.ApiExecuteFilterChain;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiInvoker;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiInvokerImp;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;
import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;

/**
 * API执行器实现类
 *
 * @author clouds
 */
public class ApiExecutorImpl implements ApiExecutor {

    /**
     * API配置
     */
    private ApiConfig apiConfig;

    /**
     * API执行过滤器
     *
     */
    private ApiExecuteFilterChain apiExecuteFilterChain;

    public ApiExecutorImpl(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public ApiExecutorImpl(ApiConfig apiConfig, ApiExecuteFilterChain apiExecuteFilterChain) {
        this.apiConfig = apiConfig;
        this.apiExecuteFilterChain = apiExecuteFilterChain;
    }

    @Override
    public ApiResult execute(ApiConfig config) {
        ApiRequest apiRequest = this.makeRequest();

        if (apiExecuteFilterChain != null) {
            apiExecuteFilterChain.doFilter(apiRequest, null);
        }

        ApiResponse apiResponse = this.sendRequest(apiRequest);
        ApiResult apiResult = this.makeResult(apiResponse);

        if (apiExecuteFilterChain != null) {
            apiExecuteFilterChain.doFilter(apiRequest, apiResponse);
        }

        return apiResult;
    }

    /**
     * 根据API配置生成API请求对象
     *
     * @return API请求对象
     */
    private ApiRequest makeRequest() {
        ApiRequest apiRequest = new ApiRequest();
        return apiRequest;
    }

    /**
     * 根据API配置生成API响应对象
     *
     * @return API响应对象
     */
    private ApiResponse sendRequest(ApiRequest request) {
        ApiInvoker apiInvoker = new ApiInvokerImp();
        ApiResponse apiResponse = apiInvoker.invoke(request);
        return apiResponse;
    }


    /**
     * 根据API响应生成API执行结果
     *
     * @return API响应对象
     */
    private ApiResult makeResult(ApiResponse response) {
        ApiResult apiResult = new ApiResult();
        return apiResult;
    }
}
