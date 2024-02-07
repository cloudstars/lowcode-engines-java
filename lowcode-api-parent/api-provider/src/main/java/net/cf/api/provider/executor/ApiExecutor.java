package net.cf.api.provider.executor;


import net.cf.api.commons.enums.ApiTypeEnum;

/**
 * 分发器定义的执行器标准
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 14:38
 */
public interface ApiExecutor {
    ApiTypeEnum getType();
    Object run(Object reqData, String bizKey);
}
