package io.github.cloudstars.lowcode.commons.lang.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置对象接口，表示某个概念的配置，它可以转化成Json对象
 *
 * @author clouds
 */
public interface XConfig {

    // 描述配置项名称
    String ATTR_DESCRIPTION  = "description";

    // 表达式配置项名称
    String ATTR_EXPRESSION = "expression";

    /**
     * 获取配置的描述
     *
     * @return
     */
    String getDescription();

    /**
     * 将配置转为 Json对象
     *
     * @return
     */
    JsonObject<String, Object> toJson();

    /**
     * 将配置转为 Json 字符串
     *
     * @return
     */
    default String toJsonString() {
        return toJson().toJsonString();
    }

}
