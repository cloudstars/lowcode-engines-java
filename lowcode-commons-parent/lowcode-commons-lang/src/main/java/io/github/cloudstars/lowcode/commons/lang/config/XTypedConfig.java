package io.github.cloudstars.lowcode.commons.lang.config;

/**
 * 特定类型的配置对象接口，具有类型、编号、名称
 *
 * @author clouds
 */
public interface XTypedConfig extends XConfig {

    /**
     * 获取配置的类型
     *
     * @return
     */
    String getType();

    /**
     * 获取配置的编号
     *
     * @return
     */
    String getKey();

    /**
     * 获取配置的名称
     *
     * @return
     */
    String getName();

}
