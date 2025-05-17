package io.github.cloudstars.lowcode.object.view.editor;

import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

/**
 * 将模型视图配置转换为组件配置的转换器
 *
 * @param <F> 模型配置类型
 * @param <T> 视图配置类型
 */
public interface XObjectViewConfigToComponentConfigConvertor<F extends XObjectViewConfig, T extends XComponentConfig> {

    /**
     * 将模型视图配置转换为组件配置
     *
     * @param viewConfig 模型属兔配置
     * @return 组件配置
     */
    T convert(F viewConfig);

}
