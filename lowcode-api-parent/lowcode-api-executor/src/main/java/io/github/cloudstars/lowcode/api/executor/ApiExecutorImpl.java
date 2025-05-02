package io.github.cloudstars.lowcode.api.executor;

import io.github.cloudstars.lowcode.api.executor.filter.ApiExecuteFilterChain;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiInvoker;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiRequest;
import io.github.cloudstars.lowcode.api.executor.invoke.ApiResponse;
import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.HttpMethod;
import io.github.cloudstars.lowcode.commons.value.ValueTypeFactory;
import io.github.cloudstars.lowcode.commons.value.XValueType;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

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
     * API调用器
     */
    private ApiInvoker apiInvoker;

    /**
     * API执行过滤器
     */
    private ApiExecuteFilterChain apiExecuteFilterChain;

    public ApiExecutorImpl(ApiInvoker apiInvoker) {
        this.apiInvoker = apiInvoker;
    }

    public ApiExecutorImpl(ApiInvoker apiInvoker, ApiExecuteFilterChain apiExecuteFilterChain) {
        this.apiInvoker = apiInvoker;
        this.apiExecuteFilterChain = apiExecuteFilterChain;
    }

    @Override
    public ApiResult execute(ApiConfig config) {
        return this.execute(apiConfig, null);
    }

    @Override
    public ApiResult execute(ApiConfig config, Object params) {
        ApiRequest apiRequest = this.makeRequest(config, params);

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
    private ApiRequest makeRequest(ApiConfig config, Object params) {
        ApiRequestConfig apiRequestConfig = config.getRequest();
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(apiRequestConfig.getServicePath());
        apiRequest.setMethod(apiRequestConfig.getMethod());
        HttpMethod apiRequestMethod = apiRequestConfig.getMethod();
        XValueTypeConfig apiRequestValueTypeConfig = apiRequestConfig.getValueType();
        if (params != null) {
            if (apiRequestMethod == HttpMethod.GET) {

            } else if (apiRequestMethod == HttpMethod.POST) {
                if (apiRequestValueTypeConfig != null) {
                    XValueType valueType = ValueTypeFactory.newInstance(apiRequestValueTypeConfig);
                    Object targetValue = valueType.mergeDefaultValue(params);
                    apiRequest.setBody(targetValue);
                } else {
                    apiRequest.setBody(params);
                }
            }
        }

        return apiRequest;
    }

    /**
     * 根据API配置生成API响应对象
     *
     * @return API响应对象
     */
    private ApiResponse sendRequest(ApiRequest request) {
        HttpMethod method = request.getMethod();
        ApiResponse apiResponse = null;
        if (method == HttpMethod.GET) {
            apiResponse = this.apiInvoker.get(request);
        } else {
            apiResponse = this.apiInvoker.post(request);
        }

        return apiResponse;
    }


    /**
     * 根据API响应生成API执行结果
     *
     * @return API响应对象
     */
    private ApiResult makeResult(ApiResponse response) {
        ApiResult apiResult = new ApiResult();
        apiResult.success(response.getBody());
        return apiResult;
    }
}
