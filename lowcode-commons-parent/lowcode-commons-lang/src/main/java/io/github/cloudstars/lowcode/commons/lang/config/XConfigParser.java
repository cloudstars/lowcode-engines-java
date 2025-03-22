package io.github.cloudstars.lowcode.commons.lang.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置对象解析器
 *
 * @param <T> 配置对象的类型
 */
public interface XConfigParser<T extends XConfig> {

    /**
     * 从JsonObject解析配置对象
     *
     * @param configJson JsonObject配置
     * @return 配置对象的实例
     */
    T fromJson(JsonObject configJson);

}
