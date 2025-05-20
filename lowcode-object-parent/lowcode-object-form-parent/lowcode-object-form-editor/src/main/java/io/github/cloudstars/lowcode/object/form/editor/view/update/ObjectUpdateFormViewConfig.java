
package io.github.cloudstars.lowcode.object.form.editor.view.update;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.form.editor.AbstractObjectFormViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

/**
 * 数据更新表单视图配置
 *
 * @author clouds
 */
@ObjectViewConfigClass(name = "UpdateForm")
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
