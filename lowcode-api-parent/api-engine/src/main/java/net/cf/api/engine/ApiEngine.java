package net.cf.api.engine;

import net.cf.api.commons.define.ApiDefine;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 11:21
 */
public interface ApiEngine {
    Object run(Object input, ApiDefine apiDefine);
}
