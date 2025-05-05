package io.github.cloudstars.lowcode.object.view.editor.table;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClass;

@ObjectViewConfigClass(name = "LOOKUP_TABLE")
public class ObjectLookupTableViewConfig extends AbstractObjectTableViewConfig {

    public ObjectLookupTableViewConfig() {
    }

    public ObjectLookupTableViewConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
