package io.github.cloudstars.lowcode.object.view;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 模型表单视图接口
 *
 * @author clouds 
 */
public interface ObjectFormView extends ObjectView {

    JsonObject toRenderSchema(FormMode mode);

}
