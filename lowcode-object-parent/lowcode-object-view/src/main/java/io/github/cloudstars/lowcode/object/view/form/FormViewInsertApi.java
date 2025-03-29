package io.github.cloudstars.lowcode.object.view.form;

import io.github.cloudstars.lowcode.commons.api.config.ApiConfig;
import io.github.cloudstars.lowcode.object.view.ObjectViewApi;

/**
 * 表单视图插入接口
 *
 * @author clouds
 */
public class FormViewInsertApi implements ObjectViewApi {

    @Override
    public String getName() {
        return "FormView.Insert";
    }

    @Override
    public ApiConfig getApiConfig() {
        return null;
    }

    @Override
    public Object execute(Object data) {
        return null;
    }
}
