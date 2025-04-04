package io.github.cloudstars.lowcode.object.view.editor;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.object.commons.config.ObjectConfig;
import io.github.cloudstars.lowcode.object.view.commons.config.AbstractViewConfig;

/**
 * 模型视图接口
 *
 * @author clouds
 * @param <C> 视图对应的组件的配置类型
 */
public interface ObjectView<V extends AbstractViewConfig, C extends AbstractConfig> {

    /**
     * 根据模型的配置更新视图的配置
     *
     * @param objectConfig
     * @return
     */
    V updateByObject(ObjectConfig objectConfig);

    /**
     * 将视图配置转换为组件配置
     *
     * @return
     */
    C toComponentConfig();
}
