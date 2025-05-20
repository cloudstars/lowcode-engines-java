
package io.github.cloudstars.lowcode.object.form.editor.view.read;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.form.editor.AbstractObjectFormViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

/**
 * 数据查看表单视图配置
 *
 * @author clouds
 */
@ObjectViewConfigClass(name = "ReadForm")
public class ObjectReadFormViewConfig extends AbstractObjectFormViewConfig {

    public ObjectReadFormViewConfig() {
    }

    public ObjectReadFormViewConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
