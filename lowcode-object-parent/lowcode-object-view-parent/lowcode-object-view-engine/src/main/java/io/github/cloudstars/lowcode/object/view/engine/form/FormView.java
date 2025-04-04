package io.github.cloudstars.lowcode.object.view.engine.form;

import io.github.cloudstars.lowcode.object.view.engine.ObjectView;
import io.github.cloudstars.lowcode.object.view.commons.config.AbstractViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.form.api.FormViewInsertApiInput;

/**
 * 模型表单视图接口
 *
 * @author clouds 
 */
public interface FormView<T extends AbstractViewConfig> extends ObjectView<T> {

    /**
     * 执行表单数据插入接口
     *
     * @param input 接口入参
     * @return 影响行数
     */
    int execute(FormViewInsertApiInput input);

}
