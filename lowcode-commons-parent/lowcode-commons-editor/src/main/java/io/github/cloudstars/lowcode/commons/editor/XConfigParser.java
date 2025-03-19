package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 配置对象解析器
 *
 * @param <T> 配置对象的类型
 */
public interface XConfigParser<T extends XConfig> {

    /**
     * 从JSON解析配置对象
     *
     * @param configJson JSON配置
     * @return 配置对象
     */
    T fromJson(JsonObject configJson);

}
