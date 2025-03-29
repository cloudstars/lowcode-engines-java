package io.github.cloudstars.lowcode.commons.lang.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置对象接口，表示某个概念的配置
 *
 * @author clouds
 */
public interface XConfig {

    /**
     * 获取配置的类型
     *
     * @return
     */
    //String getType();

    /**
     * 获取配置的名称
     *
     * @return
     */
    //String getName();

    /**
     * 将配置转为 JSON 字符串
     *
     * @return
     */
    JsonObject toJson();

}
