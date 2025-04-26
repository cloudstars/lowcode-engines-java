package io.github.cloudstars.lowcode.object.view.editor.form;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.object.view.commons.config.AbstractViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.ObjectView;

/**
 * 模型表单视图接口
 *
 * @author clouds 
 */
public interface FormView<V extends AbstractViewConfig, T extends AbstractConfig> extends ObjectView<V, T> {
}
