package io.github.cloudstars.lowcode.object.table.editor;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

@ObjectViewConfigClass(name = "DATA_TABLE")
public class ObjectDataTableViewConfig extends AbstractObjectTableViewConfig {

    public ObjectDataTableViewConfig() {
    }

    public ObjectDataTableViewConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
