package net.cf.api.proxy.engine.filter.builtin.auth.token;

/**
 * token管理器
 * @author 80345746
 * @version v1.0
 * @date 2024/1/30 19:18
 */
public interface TokenManager {
    Token getToken();
    void removeToken();
}
