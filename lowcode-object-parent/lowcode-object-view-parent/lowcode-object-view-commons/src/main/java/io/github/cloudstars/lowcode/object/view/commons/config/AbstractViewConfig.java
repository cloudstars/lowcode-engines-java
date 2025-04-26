package io.github.cloudstars.lowcode.object.view.commons.config;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的视图配置类型
 *
 * @author clouds
 */
public abstract class AbstractViewConfig extends AbstractResourceConfig {

    /**
     * 视图的编号
     */
     private String key;

     /**
     * 视图归属的模型名称
     */
    private String objectName;

    public AbstractViewConfig() {
    }

    public AbstractViewConfig(JsonObject configJson) {
        super(configJson);

        this.setKey((String) configJson.get("key"));
        this.setObjectName((String) configJson.get("objectName"));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put("key", this.key);
        configJson.put("objectName", this.objectName);

        return configJson;
    }

}
