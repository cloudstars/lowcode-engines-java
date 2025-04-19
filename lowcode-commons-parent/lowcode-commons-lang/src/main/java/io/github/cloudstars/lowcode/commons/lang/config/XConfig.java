package io.github.cloudstars.lowcode.commons.lang.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置对象接口，表示某个概念的配置，它可以转化成Json对象
 *
 * @author clouds
 */
public interface XConfig {

    // 类型属性名称
    String ATTR_TYPE = "type";

    // 编号属性名称
    String ATTR_KEY = "key";

    // 名称属性名称
    String ATTR_NAME = "name";

    // 标题属性名称
    String ATTR_TITLE = "title";

    // 值属性名称
    String ATTR_VALUE = "value";

    /**
     * 将配置转为 JSON 字符串
     *
     * @return
     */
    JsonObject toJson();

}
