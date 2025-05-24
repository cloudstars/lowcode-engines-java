package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置对象序列化接口
 *
 * @author clouds
 */
public interface XConfigSerializer<T extends XConfig> {

    /**
     * 将配置序列号为Json对象
     *
     * @param config 配置
     * @return 配置Json对象
     */
    JsonObject toJson(T config);

}
