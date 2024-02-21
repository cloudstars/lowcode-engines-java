package net.cf.api.proxy.engine.filter;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;

/**
 * 过滤器接口
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 11:10
 */
public interface ExecuteFilter {
    /**
     * 前置处理
     * @param httpApiRequest 请求数据
     */
    void preHandle(HttpApiRequest httpApiRequest);

    /**
     * 后置处理
     * @param httpApiRequest 请求数据
     * @param httpApiResponse 响应数据
     */
    void postHandle(HttpApiRequest httpApiRequest, HttpApiResponse httpApiResponse);
}
