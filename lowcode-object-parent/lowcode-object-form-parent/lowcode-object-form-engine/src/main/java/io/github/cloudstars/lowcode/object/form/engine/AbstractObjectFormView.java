package io.github.cloudstars.lowcode.object.form.engine;

import io.github.cloudstars.lowcode.object.form.editor.AbstractObjectFormViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.AbstractObjectView;

/**
 * 抽象的模型表单视图
 *
 * @param <T> 表单视图配置类型
 */
public abstract class AbstractObjectFormView<T extends AbstractObjectFormViewConfig> extends AbstractObjectView<T> {

    public AbstractObjectFormView(T viewConfig) {
        super(viewConfig);
    }

}
