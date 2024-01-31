package net.cf.api.engine;


import net.cf.api.commons.enums.ApiTypeEnum;
import net.cf.api.provider.executor.ApiExecutor;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 15:13
 */
public interface ApiExecutorRegistration {
    void register(ApiExecutor apiExecutor);
    ApiExecutor find(ApiTypeEnum apiType);
}
