package io.github.cloudstars.lowcode.commons.config;

/**
 * 特定资源的配置（资源具有类型、代码、名称，如模型、视图等）
 *
 * @author clouds
 */
public interface XResourceConfig extends XTypedConfig {

    // 编号属性名称
    String ATTR_KEY = "key";

    // 代码属性名称
    String ATTR_CODE = "code";

    // 名称属性名称
    String ATTR_NAME = "name";

    /**
     * 获取配置的编号
     *
     * @return
     */
    String getKey();

    /**
     * 获取配置的代码
     *
     * @return
     */
    String getCode();

    /**
     * 获取配置的名称
     *
     * @return
     */
    String getName();

}
