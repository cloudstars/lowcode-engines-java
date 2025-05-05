package io.github.cloudstars.lowcode.object.method.editor;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 单条更新模型方法
 *
 * @author clouds
 */
@ObjectMethodConfigClass(name = "UPDATE_ONE")
public class ObjectUpdateOneMethodConfig extends AbstractObjectMethodConfig {

    /**
     * 本表的字段列表
     */
    private List<String> fieldKeys;

    public ObjectUpdateOneMethodConfig() {
    }

    public ObjectUpdateOneMethodConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
