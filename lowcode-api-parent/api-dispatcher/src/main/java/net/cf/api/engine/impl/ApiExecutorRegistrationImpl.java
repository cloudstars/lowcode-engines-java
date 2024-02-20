package net.cf.api.engine.impl;

import net.cf.api.commons.enums.ApiTypeEnum;
import net.cf.api.engine.ApiExecutorRegistration;
import net.cf.api.provider.executor.ApiExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * api执行器注册中心
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 15:19
 */
public class ApiExecutorRegistrationImpl implements ApiExecutorRegistration {

    private Map<ApiTypeEnum, ApiExecutor> executorMap = new HashMap<>(8);

    @Override
    public void register(ApiExecutor apiExecutor) {
        executorMap.put(apiExecutor.getType(), apiExecutor);
    }

    @Override
    public ApiExecutor find(ApiTypeEnum apiType) {
        return executorMap.get(apiType);
    }

}
