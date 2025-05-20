package io.github.cloudstars.lowcode.object.view.editor.convert;

import io.github.cloudstars.lowcode.object.commons.XObjectConfig;
import io.github.cloudstars.lowcode.object.commons.XObjectConfigResolver;
import io.github.cloudstars.lowcode.object.view.editor.XObjectViewConfig;

/**
 * 抽象的模型配置转换为视图配置的转换器（各种实现类默认继承它）
 *
 * @param <F> 模型配置类型
 * @param <T> 视图配置类型
 */
public abstract class AbstractObjectConfig2ViewConfigConvertor<F extends XObjectConfig, T extends XObjectViewConfig> implements XObjectConfig2ViewConfigConvertor<F, T> {

    /**
     * 模型配置解析器
     */
    protected XObjectConfigResolver resolver;

    public AbstractObjectConfig2ViewConfigConvertor(XObjectConfigResolver resolver) {
        this.resolver = resolver;
    }

}
