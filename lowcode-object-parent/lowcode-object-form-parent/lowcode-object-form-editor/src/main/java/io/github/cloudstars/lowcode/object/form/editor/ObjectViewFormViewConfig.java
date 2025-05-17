
package io.github.cloudstars.lowcode.object.form.editor;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

/**
 * 数据查看表单视图配置
 *
 * @author clouds
 */
@ObjectViewConfigClass(name = "VIEW_FORM")
public class ObjectViewFormViewConfig extends AbstractObjectFormViewConfig {

    public ObjectViewFormViewConfig() {
    }

    public ObjectViewFormViewConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
