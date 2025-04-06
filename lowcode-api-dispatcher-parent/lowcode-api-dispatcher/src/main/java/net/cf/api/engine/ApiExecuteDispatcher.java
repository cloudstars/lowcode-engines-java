package net.cf.api.engine;

/**
 * api执行器分发器
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 11:21
 */
public interface ApiExecuteDispatcher {
    /**
     * api分发请求
     * @param input 输入
     * @param apiKey apiKey
     * @return 结果
     */
    Object dispatch(Object input, String apiKey);
}
