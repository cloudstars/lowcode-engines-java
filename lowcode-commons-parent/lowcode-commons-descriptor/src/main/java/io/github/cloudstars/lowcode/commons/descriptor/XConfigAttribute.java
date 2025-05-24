package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置的属性
 *
 * @author clouds
 * @param <T> 配置类型
 */
public interface XConfigAttribute<T extends XConfig> {

    /**
     * 获取属性的名称
     *
     * @return 属性名称
     */
    String getName();

    /**
     * 获取属性的标题
     *
     * @return 属性标题
     */
    String getLabel();

    /**
     * 从Json配置构建属性
     *
     * @param config
     * @param configJson
     */
    void fromJson(JsonObject configJson, T config);

    /**
     * 从配置构建Json
     *
     * @param config
     * @param configJson
     */
    void toJson(T config, JsonObject configJson);

}
