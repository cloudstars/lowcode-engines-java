package net.cf.api.engine;


import net.cf.api.commons.enums.ApiTypeEnum;
import net.cf.api.provider.executor.ApiExecutor;

/**
 * api执行器注册中心
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 15:13
 */
public interface ApiExecutorRegistration {
    /**
     * 注册一个执行器
     * @param apiExecutor apiExecutor
     */
    void register(ApiExecutor apiExecutor);

    /**
     * 通过api类型查找一个执行器
     * @param apiType api类型
     * @return 执行器
     */
    ApiExecutor find(ApiTypeEnum apiType);
}
