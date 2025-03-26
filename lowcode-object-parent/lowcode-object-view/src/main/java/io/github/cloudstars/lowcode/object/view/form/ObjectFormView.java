package io.github.cloudstars.lowcode.object.view.form;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.ObjectView;

/**
 * 模型表单视图接口
 *
 * @author clouds 
 */
public interface ObjectFormView extends ObjectView {

    JsonObject toRenderSchema();

}
