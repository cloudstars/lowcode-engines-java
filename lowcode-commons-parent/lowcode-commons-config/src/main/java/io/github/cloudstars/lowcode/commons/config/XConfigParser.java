package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置对象解析接口
 *
 * @author clouds
 * @see XConfigDeserializer
 * @see XConfigSerializer
 */
@Deprecated
public interface XConfigParser<T extends XConfig> {

    /**
     * 解析JSON配置为配置对象
     *
     * @param configJson 配置JSON
     * @return 配置对象
     */
    T parse(JsonObject configJson);

}
