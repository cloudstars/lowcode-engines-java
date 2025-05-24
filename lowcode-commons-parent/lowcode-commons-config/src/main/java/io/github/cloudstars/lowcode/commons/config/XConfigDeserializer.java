package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置反序列化接口
 *
 * @author clouds
 */
public interface XConfigDeserializer<T extends XConfig> {

    /**
     * 解析Json对象为配置
     *
     * @param configJson Json对象
     * @return 配置
     */
    T parse(JsonObject configJson);

}
