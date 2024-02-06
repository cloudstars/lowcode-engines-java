package net.cf.api.proxy.engine.filter.chain;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;
import net.cf.api.proxy.engine.filter.ExecuteFilter;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 16:14
 */
public interface ExecuteFilterChain {
    void addFilter(ExecuteFilter executeFilter);
    void applyPreHandler(HttpApiRequest httpApiRequest);
    void applyPostHandler(HttpApiRequest httpApiRequest, HttpApiResponse httpApiResponse);
}
