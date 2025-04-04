package io.github.cloudstars.lowcode.object.view.engine.form;

import io.github.cloudstars.lowcode.object.view.engine.form.api.FormViewInsertApiInput;

public class FormViewServiceImpl implements FormViewService {

    @Override
    public Integer execute(String viewKey, FormViewInsertApiInput input) {
        FormView view = this.resolveFormView(viewKey);
        return view.execute(input);
    }


    private FormView resolveFormView(String viewKey) {
        // TODO 根据viewKey生成FormView
        return new FormViewImpl(null);
    }

}
