package io.github.cloudstars.lowcode.object.view.editor.convert;

import io.github.cloudstars.lowcode.object.method.editor.XObjectMethodConfig;
import io.github.cloudstars.lowcode.object.view.editor.XObjectViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.action.XObjectViewActionConfig;

/**
 * 模型视图配置到模型方法配置的转换器
 *
 * @param <C> 模型视图配置类型
 * @param <T> 模型方法配置类型
 */
public interface XObjectViewConfig2MethodConfigConvertor<C extends XObjectViewConfig, A extends XObjectViewActionConfig, T extends XObjectMethodConfig> {

    /**
     * 将视图配置转为方法配置
     *
     * @param viewConfig 视图配置
     * @return 方法配置
     */
    T convert(C viewConfig, A actionConfig);

}
