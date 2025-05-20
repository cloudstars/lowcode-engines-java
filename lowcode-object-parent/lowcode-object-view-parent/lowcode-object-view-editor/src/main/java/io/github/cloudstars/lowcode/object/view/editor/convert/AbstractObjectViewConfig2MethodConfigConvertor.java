package io.github.cloudstars.lowcode.object.view.editor.convert;

import io.github.cloudstars.lowcode.object.commons.XObjectConfigResolver;
import io.github.cloudstars.lowcode.object.method.editor.XObjectMethodConfig;
import io.github.cloudstars.lowcode.object.view.editor.XObjectViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.action.XObjectViewActionConfig;

/**
 * 抽象的模型视图配置转换为接口配置的转换器（各种实现类默认继承它）
 *
 * @param <F> 模型配置类型
 * @param <T> 视图配置类型
 */
public abstract class AbstractObjectViewConfig2MethodConfigConvertor<F extends XObjectViewConfig, A extends XObjectViewActionConfig, T extends XObjectMethodConfig> implements XObjectViewConfig2MethodConfigConvertor<F, A, T> {

    /**
     * 模型配置解析器
     */
    protected XObjectConfigResolver resolver;

    public AbstractObjectViewConfig2MethodConfigConvertor(XObjectConfigResolver resolver) {
        this.resolver = resolver;
    }

}
