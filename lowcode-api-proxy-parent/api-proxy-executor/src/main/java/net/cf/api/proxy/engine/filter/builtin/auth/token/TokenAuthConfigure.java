package net.cf.api.proxy.engine.filter.builtin.auth.token;

import net.cf.api.proxy.engine.enums.TokenPosEnum;


/**
 * token认证配置
 * @author 80345746
 * @version v1.0
 * @date 2024/1/30 19:37
 */
public interface TokenAuthConfigure {
    TokenPosEnum getTargetPos();

    String getTargetKey();
}
