package io.github.cloudstars.lowcode.api.executor.invoke;

/**
 * API调用器
 *
 * @author clouds
 */
public interface ApiInvoker {

    /**
     * 发送 Get 请求
     *
     * @param request API请求对象
     * @return API响应结果
     */
    ApiResponse get(ApiRequest request);

    /**
     * 发送 Post 请求
     *
     * @param request
     * @return
     */
    ApiResponse post(ApiRequest request);

}
