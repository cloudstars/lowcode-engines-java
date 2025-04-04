package io.github.cloudstars.lowcode.object.view.editor.form;

import io.github.cloudstars.lowcode.object.commons.config.ObjectConfig;
import io.github.cloudstars.lowcode.object.view.commons.config.form.FormViewConfig;

/**
 * 模型表单视图实现
 *
 * @author clouds 
 */
public class FormViewImpl implements FormView<FormViewConfig, FormConfig> {

    private FormViewConfig viewConfig;

    public FormViewImpl(FormViewConfig viewConfig) {
        this.viewConfig = viewConfig;
    }

    @Override
    public FormViewConfig updateByObject(ObjectConfig objectConfig) {
        return null;
    }

    @Override
    public FormConfig toComponentConfig() {
        return null;
    }

}
