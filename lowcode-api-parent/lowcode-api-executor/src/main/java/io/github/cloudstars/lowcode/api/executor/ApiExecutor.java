package io.github.cloudstars.lowcode.api.executor;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;

/**
 * API执行器
 *
 * @author clouds
 */
public interface ApiExecutor {

    /**
     * 执行API
     *
     * @param config API配置
     * @return API执行结果
     */
    ApiResult execute(ApiConfig config);

    /**
     * 带参数执行API
     *
     * @param config API配置
     * @param params API参数
     * @return API执行结果
     */
    ApiResult execute(ApiConfig config, Object params);


}
