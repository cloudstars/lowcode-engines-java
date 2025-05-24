package io.github.cloudstars.lowcode.object.commons;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的模型相关的资源配置
 *
 * @author clouds
 */
public class AbstractObjectResourceConfig extends AbstractResourceConfig {

    private static final String ATTR_OBJECT_KEY = "objectKey";

    private String objectKey;

    public AbstractObjectResourceConfig() {
    }

    public AbstractObjectResourceConfig(JsonObject configJson) {
        super(configJson);

        this.objectKey = ConfigUtils.getString(configJson, ATTR_OBJECT_KEY);
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putRequired(configJson, ATTR_OBJECT_KEY, this.objectKey);

        return configJson;
    }
}
