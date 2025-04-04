package io.github.cloudstars.lowcode.object.view.engine.form;

import io.github.cloudstars.lowcode.object.view.commons.config.form.FormViewConfig;
import io.github.cloudstars.lowcode.object.view.engine.form.api.FormViewInsertApi;
import io.github.cloudstars.lowcode.object.view.engine.form.api.FormViewInsertApiInput;

/**
 * 模型表单视图实现
 *
 * @author clouds 
 */
public class FormViewImpl implements FormView<FormViewConfig> {

    private FormViewConfig viewConfig;

    public FormViewImpl(FormViewConfig viewConfig) {
        this.viewConfig = viewConfig;
    }

    @Override
    public int execute(FormViewInsertApiInput input) {
        FormViewInsertApi api = this.loadFormViewInsertApi();
        return api.execute(input);
    }

    /**
     * 加载表单视图单条插入接口
     *
     * @return
     */
    private FormViewInsertApi loadFormViewInsertApi() {
        return null;
    }

}
