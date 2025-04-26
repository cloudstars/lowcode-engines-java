package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 某种配置类型
 *
 * @author clouds
 */
public class SomeConfig extends AbstractConfig {

    private String x;

    private List<SomeAttribute> items;

    public SomeConfig() {
    }

    public SomeConfig(JsonObject configJson) {
        super(configJson);
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public List<SomeAttribute> getItems() {
        return items;
    }

    public void setItems(List<SomeAttribute> items) {
        this.items = items;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = new JsonObject();
        configJson.put("x", x);
        configJson.put("items", items);
        return configJson;
    }

}
