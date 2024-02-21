package net.cf.api.proxy.engine.filter.chain;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;
import net.cf.api.proxy.engine.filter.ExecuteFilter;

/**
 * 执行器过滤链
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 16:14
 */
public interface ExecuteFilterChain {
    /**
     * 增加一个过滤器
     * @param executeFilter 过滤器
     */
    void addFilter(ExecuteFilter executeFilter);

    /**
     * 执行前置处理
     * @param httpApiRequest 请求数据
     */
    void applyPreHandler(HttpApiRequest httpApiRequest);

    /**
     * 执行后置处理
     * @param httpApiRequest  请求数据
     * @param httpApiResponse 响应数据
     */
    void applyPostHandler(HttpApiRequest httpApiRequest, HttpApiResponse httpApiResponse);
}
