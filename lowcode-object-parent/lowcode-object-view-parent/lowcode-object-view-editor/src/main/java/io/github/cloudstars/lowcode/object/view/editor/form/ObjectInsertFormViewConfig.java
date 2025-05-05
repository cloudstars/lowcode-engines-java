
package io.github.cloudstars.lowcode.object.view.editor.form;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

/**
 * 数据插入表单视图配置
 *
 * @author clouds
 */
@ObjectViewConfigClass(name = "INSERT_FORM")
public class ObjectInsertFormViewConfig extends AbstractObjectFormViewConfig {

    public ObjectInsertFormViewConfig() {
    }

    public ObjectInsertFormViewConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }
}
