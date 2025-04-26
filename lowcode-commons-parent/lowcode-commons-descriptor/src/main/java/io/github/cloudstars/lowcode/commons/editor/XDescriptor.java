package io.github.cloudstars.lowcode.commons.editor;

import java.util.List;

/**
 * 规范接口，用于对某个配置进行规范性描述
 *
 * @author clouds
 */
public interface XDescriptor {

    /**
     * 获取规范的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取配置对象的类型
     * @return
     */
    String getConfigClassName();

    /**
     * 获取规范的属性列表
     *
     * @return
     */
    List<ConfigAttribute> getAttributes();

}

