package io.github.cloudstars.lowcode.commons.lang.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置对象接口，表示某个概念的配置
 *
 * @author clouds
 */
public abstract class AbstractConfig implements XConfig {

    /**
     * 配置的类型
     */
    //private String type;

    public AbstractConfig() {
    }

    public AbstractConfig(JsonObject configJson) {
    }

    /**
     * 配置的名称
     */
    //private String name;

    /*@Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }*/



    @Override
    public JsonObject toJson() {
        JsonObject configJson = new JsonObject();
        //configJson.put("type", this.type);
        //configJson.put("name", this.name);
        return configJson;
    }

}
