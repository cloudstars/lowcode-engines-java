
package io.github.cloudstars.lowcode.object.view.editor.form;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

/**
 * 数据更新表单视图配置
 *
 * @author clouds
 */
@ObjectViewConfigClass(name = "UPDATE_FORM")
public class ObjectUpdateFormViewConfig extends AbstractObjectFormViewConfig {

    public ObjectUpdateFormViewConfig() {
    }

    public ObjectUpdateFormViewConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
