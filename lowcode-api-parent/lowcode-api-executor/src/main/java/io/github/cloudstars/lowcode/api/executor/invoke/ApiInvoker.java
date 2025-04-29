package io.github.cloudstars.lowcode.api.executor.invoke;

/**
 * API调用器
 *
 * @author clouds
 */
public interface ApiInvoker {

    /**
     * 执行一个API调用
     *
     * @param request API请求对象
     * @return API响尖对象
     */
    ApiResponse invoke(ApiRequest request);

}
