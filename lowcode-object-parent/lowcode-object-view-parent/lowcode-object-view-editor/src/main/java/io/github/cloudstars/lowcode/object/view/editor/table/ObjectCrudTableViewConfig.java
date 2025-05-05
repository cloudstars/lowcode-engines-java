package io.github.cloudstars.lowcode.object.view.editor.table;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

@ObjectViewConfigClass(name = "CURD_TABLE")
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
