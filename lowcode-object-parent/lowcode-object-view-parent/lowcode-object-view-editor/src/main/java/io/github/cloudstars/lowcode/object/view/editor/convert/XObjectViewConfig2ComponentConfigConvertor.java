package io.github.cloudstars.lowcode.object.view.editor.convert;

import io.github.cloudstars.lowcode.component.commons.XComponentConfig;
import io.github.cloudstars.lowcode.object.view.editor.XObjectViewConfig;

/**
 * 将模型视图配置转换为组件配置的转换器
 *
 * @param <F> 模型配置类型
 * @param <T> 视图配置类型
 */
public interface XObjectViewConfig2ComponentConfigConvertor<F extends XObjectViewConfig, T extends XComponentConfig> {

    /**
     * 将模型视图配置转换为组件配置
     *
     * @param viewConfig 模型属兔配置
     * @return 组件配置
     */
    T convert(F viewConfig);

}
