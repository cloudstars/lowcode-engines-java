package io.github.cloudstars.lowcode.object.view.form;

import io.github.cloudstars.lowcode.commons.data.valuetype.ValueTypeConfig;
import io.github.cloudstars.lowcode.object.view.ViewApi;

/**
 * 表单视图插入接口
 *
 * @author clouds
 */
public class FormViewInsertApi implements ViewApi {

    @Override
    public String getName() {
        return "FormView.Insert";
    }

    @Override
    public ValueTypeConfig getInputDataType() {
        return null;
    }

    @Override
    public ValueTypeConfig getOutputDataType() {
        return null;
    }

    @Override
    public Object execute(Object data) {
        return null;
    }
}
