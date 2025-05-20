package io.github.cloudstars.lowcode.object.table.editor;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

/**
 * CURD表格视图
 *
 * @author clouds
 */
@ObjectViewConfigClass(name = "CurdTable")
public class ObjectCrudTableViewConfig extends AbstractObjectTableViewConfig {

    public ObjectCrudTableViewConfig() {
    }

    public ObjectCrudTableViewConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
