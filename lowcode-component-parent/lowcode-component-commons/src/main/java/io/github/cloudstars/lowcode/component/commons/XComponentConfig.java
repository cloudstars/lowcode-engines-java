package io.github.cloudstars.lowcode.component.commons;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;

/**
 * 组件配置接口
 *
 * @author clouds
 */
public interface XComponentConfig extends XTypedConfig {

    /**
     * 获取组件的ID
     *
     * @return 组件ID（并非所有的组件都会定义ID）
     */
    default String getId() {
        return null;
    }

    /**
     * 获取组件的类型
     *
     * @return 组件类型
     */
    String getType();

}
