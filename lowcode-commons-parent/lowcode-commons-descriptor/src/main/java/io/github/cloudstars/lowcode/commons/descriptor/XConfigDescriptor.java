package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.XConfig;

import java.util.List;

/**
 * 配置的规范接口，用于对某个配置进行规范性描述
 *
 * @author clouds
 */
public interface XConfigDescriptor<T extends XConfig> {

    /**
     * 获取配置的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取配置的类
     *
     * @return
     */
    Class<T> getConfigClass();

    /**
     * 获取配置的属性列表
     *
     * @return
     */
    List<XConfigAttribute> getAttributes();

}

