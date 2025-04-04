package io.github.cloudstars.lowcode.object.view.engine.form;

import io.github.cloudstars.lowcode.object.view.engine.form.api.FormViewInsertApiInput;

public interface FormViewService {

    /**
     * 执行单视图查询接口
     *
     * @param viewKey
     * @param input
     * @return 列表数据
     */
     Integer execute(String viewKey, FormViewInsertApiInput input);

}
