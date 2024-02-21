package net.cf.api.proxy.engine.filter.builtin.auth.token;

import net.cf.api.proxy.engine.enums.TokenPosEnum;


/**
 * token认证配置
 * @author 80345746
 * @version v1.0
 * @date 2024/1/30 19:37
 */
public interface TokenAuthConfigure {
    /**
     * 获取token传递的位置
     * @return 位置信息
     */
    TokenPosEnum getTargetPos();

    /**
     * 获取存放token的key
     * @return 返回存放token的key
     */
    String getTargetKey();
}
