package net.cf.api.proxy.engine;

import net.cf.api.proxy.engine.entity.HttpApiRequest;
import net.cf.api.proxy.engine.entity.HttpApiResponse;

/**
 * api代理执行器
 * @author 80345746
 * @version v1.0
 * @date 2024/1/29 11:46
 */
public interface ApiProxyExecutor {
    /**
     * api代理执行器
     * @param httpApiRequest api执行请求
     * @return api执行结果
     */
    HttpApiResponse execute(HttpApiRequest httpApiRequest);
}
