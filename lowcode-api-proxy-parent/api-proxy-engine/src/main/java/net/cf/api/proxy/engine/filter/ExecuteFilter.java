package net.cf.api.proxy.engine.filter;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 11:10
 */
public interface ExecuteFilter {
    void preHandle(HttpApiRequest httpApiRequest);

    void postHandle(HttpApiRequest httpApiRequest, HttpApiResponse httpApiResponse);
}
