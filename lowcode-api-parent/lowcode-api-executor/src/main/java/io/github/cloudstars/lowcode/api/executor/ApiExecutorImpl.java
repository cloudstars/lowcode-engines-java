package io.github.cloudstars.lowcode.api.executor;

import io.github.cloudstars.lowcode.api.executor.filter.ApiExecuteFilterChain;
import io.github.cloudstars.lowcode.api.executor.invoke.*;
import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestBodyConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestConfig;
import io.github.cloudstars.lowcode.commons.api.config.request.HttpMethod;
import io.github.cloudstars.lowcode.commons.api.config.request.RequestContentTypeEnum;
import io.github.cloudstars.lowcode.commons.lang.exception.SystemException;
import io.github.cloudstars.lowcode.commons.value.ValueTypeFactory;
import io.github.cloudstars.lowcode.commons.value.XValueType;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

import java.util.List;
import java.util.Map;

/**
 * API执行器实现类
 *
 * @author clouds
 */
public class ApiExecutorImpl implements ApiExecutor {

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
    public ApiResult execute(ApiConfig apiConfig) {
        return this.execute(apiConfig, null);
    }

    @Override
    public ApiResult execute(ApiConfig apiConfig, Object params) {
        ApiRequest apiRequest = this.makeRequest(apiConfig, params);

        if (apiExecuteFilterChain != null) {
            apiExecuteFilterChain.doFilter(apiRequest, null);
        }

        ApiResponse apiResponse = this.sendRequest(apiConfig, apiRequest);
        ApiResult apiResult = this.makeResult(apiConfig, apiResponse);

        if (apiExecuteFilterChain != null) {
            apiExecuteFilterChain.doFilter(apiRequest, apiResponse);
        }

        return apiResult;
    }

    /**
     * 根据API配置及参数生成API请求对象
     *
     * @param config API配置
     * @param params API请求参数
     * @return API请求对象
     */
    private ApiRequest makeRequest(ApiConfig config, Object params) {
        ApiRequestConfig apiRequestConfig = config.getRequest();
        HttpMethod apiRequestMethod = apiRequestConfig.getMethod();
        ApiRequest apiRequest = null;
        if (apiRequestMethod == HttpMethod.GET) {
            apiRequest = this.makeGetRequest(config, params);
        } else if (apiRequestMethod == HttpMethod.POST) {
            apiRequest = this.makePostRequest(config, params);
        } else {
            throw new SystemException("不支持的HTTP方法：" + apiRequestMethod);
        }

        return apiRequest;
    }

    /**
     * 根据API配置生成API Get请求对象
     *
     * @param config API配置
     * @param params API请求参数
     * @return Api Get请求
     */
    private ApiRequest makeGetRequest(ApiConfig config, Object params) {
        ApiRequestConfig apiRequestConfig = config.getRequest();
        ApiRequest apiRequest = this.makeInitApiRequest(apiRequestConfig);
        RequestContentTypeEnum contentType = apiRequestConfig.getContentType();
        if (params != null) {
            if (contentType != RequestContentTypeEnum.MULTIPART_FORM_DATA) {
                apiRequest.setQueryParams((Map<String, Object>) params);
            } else {
                throw new SystemException("Get请求不支持ContentType：" + contentType);
            }
        }

        return apiRequest;
    }

    /**
     * 根据API配置生成API Post请求对象
     *
     * @param config API配置
     * @param params API请求参数
     * @return Api Post请求
     */
    private ApiRequest makePostRequest(ApiConfig config, Object params) {
        ApiRequestConfig apiRequestConfig = config.getRequest();
        ApiRequest apiRequest = this.makeInitApiRequest(apiRequestConfig);

        Object targetValue = params;
        RequestContentTypeEnum requestContentType = apiRequestConfig.getContentType();
        ApiRequestBodyConfig apiRequestBodyConfig = apiRequestConfig.getBody();
        XValueTypeConfig apiRequestValueTypeConfig = apiRequestBodyConfig.getValueType();
        if (apiRequestValueTypeConfig != null) {
            XValueType valueType = ValueTypeFactory.newInstance(apiRequestValueTypeConfig);
            targetValue = valueType.mergeDefaultValue(params);
        }
        if (requestContentType == RequestContentTypeEnum.APPLICATION_JSON) {
            apiRequest.setBody(targetValue);
        } else {
            apiRequest.setFormItems((Map<String, List<Object>>) targetValue);
        }

        return apiRequest;
    }

    /**
     * 创建一个初始人API请求
     *
     * @param apiRequestConfig API请求配置
     * @return 初始API请求
     */
    private ApiRequest makeInitApiRequest(ApiRequestConfig apiRequestConfig) {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setUrl(apiRequestConfig.getServicePath());
        apiRequest.setMethod(apiRequestConfig.getMethod());
        return apiRequest;
    }

    private void setApiParams(AbstractApiRequest apiRequest, Object params) {
        if (params != null) {

        }
    }

    /**
     * 发送请求
     *
     * @param apiConfig  API配置
     * @param apiRequest API请求
     * @return API响应
     */
    private ApiResponse sendRequest(ApiConfig apiConfig, ApiRequest apiRequest) {
        ApiRequestConfig apiRequestConfig = apiConfig.getRequest();
        ApiResponse apiResponse = null;
        HttpMethod method = apiRequestConfig.getMethod();
        if (method == HttpMethod.GET) {
            apiResponse = this.sendGetRequest(apiRequest);
        } else {
            RequestContentTypeEnum apiRequestContentType = apiRequestConfig.getContentType();
            if (apiRequestContentType == RequestContentTypeEnum.MULTIPART_FORM_DATA) {
                apiResponse = this.sendFileUploadRequest(apiRequest);
            } else {
                apiResponse = this.sendPostRequest(apiRequest);
            }
        }

        return apiResponse;
    }

    /**
     * 发送 Get 请求
     *
     * @return API响应对象
     */
    private ApiResponse sendGetRequest(ApiRequest request) {
        ApiResponse apiResponse = this.apiInvoker.get(request);
        return apiResponse;
    }

    /**
     * 发送 Post 请求
     *
     * @return API响应对象
     */
    private ApiResponse sendPostRequest(ApiRequest request) {
        ApiResponse apiResponse = this.apiInvoker.post(request);
        return apiResponse;
    }

    /**
     * 发送文件上传请求
     *
     * @param request API请求
     * @return API响应
     */
    private ApiResponse sendFileUploadRequest(ApiRequest request) {
        ApiFileUploadRequest fileUploadRequest = new ApiFileUploadRequest();
        fileUploadRequest.setUrl(request.getUrl());
        fileUploadRequest.setHeaders(request.getHeaders());
        fileUploadRequest.setFormItems(request.getFormItems());
        ApiResponse apiResponse = this.apiInvoker.uploadFile(fileUploadRequest);
        return apiResponse;
    }


    /**
     * 根据API响应生成API执行结果
     *
     * @param apiConfig   API配置
     * @param apiResponse API响应
     * @return API执行结果
     */
    private ApiResult makeResult(ApiConfig apiConfig, ApiResponse apiResponse) {
        ApiResult apiResult = new ApiResult();
        apiResult.success(apiResponse.getBody());
        return apiResult;
    }
}
