package io.github.cloudstars.lowcode.object.method.editor;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 单条插入模型方法
 *
 * @author clouds
 */
@ObjectMethodConfigClass(name = "INSERT_ONE")
public class ObjectInsertOneMethodConfig extends AbstractObjectMethodConfig {

    public ObjectInsertOneMethodConfig() {
    }

    public ObjectInsertOneMethodConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
