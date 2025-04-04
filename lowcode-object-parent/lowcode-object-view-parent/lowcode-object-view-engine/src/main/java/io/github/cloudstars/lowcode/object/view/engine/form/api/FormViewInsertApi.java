package io.github.cloudstars.lowcode.object.view.engine.form.api;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.object.view.engine.ObjectViewApi;

/**
 * 表单视图插入接口
 *
 * @author clouds
 */
public class FormViewInsertApi implements ObjectViewApi<FormViewInsertApiInput, Integer> {

    @Override
    public String getName() {
        return "FormView.Insert";
    }

    @Override
    public ApiConfig getApiConfig() {
        return null;
    }

    @Override
    public Integer execute(FormViewInsertApiInput formData) {
        return null;
    }
}
