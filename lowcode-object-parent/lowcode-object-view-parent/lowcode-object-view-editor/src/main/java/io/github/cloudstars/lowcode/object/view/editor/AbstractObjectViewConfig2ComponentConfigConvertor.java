package io.github.cloudstars.lowcode.object.view.editor;

import io.github.cloudstars.lowcode.component.commons.XComponentConfig;
import io.github.cloudstars.lowcode.object.commons.XObjectConfigResolver;

/**
 * 将模型视图配置转换为组件配置的转换器
 *
 * @param <F> 模型配置类型
 * @param <T> 视图配置类型
 */
public abstract class AbstractObjectViewConfig2ComponentConfigConvertor<F extends XObjectViewConfig, T extends XComponentConfig> implements XObjectViewConfigToComponentConfigConvertor<F, T> {

    /**
     * 模型配置解析器
     */
    protected XObjectConfigResolver resolver;

    public AbstractObjectViewConfig2ComponentConfigConvertor(XObjectConfigResolver resolver) {
        this.resolver = resolver;
    }

}
