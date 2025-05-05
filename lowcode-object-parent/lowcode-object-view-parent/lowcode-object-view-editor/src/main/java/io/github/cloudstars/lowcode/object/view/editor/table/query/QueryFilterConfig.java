package io.github.cloudstars.lowcode.object.view.editor.table.query;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 过滤条件配置
 */
public class QueryFilterConfig extends AbstractConfig {

    /**
     * 过滤条件的类型
     */
    private Type type;

    /**
     * 当过滤条件是OQL时，代表OQL语句
     */
    private String oqlText;

    public QueryFilterConfig() {
    }

    public QueryFilterConfig(JsonObject configJson) {
        super(configJson);

        String typeValue = (String) configJson.get("type");
        Type type = Type.valueOf(typeValue.toUpperCase());
        this.type = type;
        if (type == Type.OQL) {
            this.oqlText = (String) configJson.get("oqlText");
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getOqlText() {
        return oqlText;
    }

    public void setOqlText(String oqlText) {
        this.oqlText = oqlText;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put("filterType", this.type.name());
        if (this.type == Type.OQL) {
            configJson.put("oqlText", this.oqlText);
        }

        return configJson;
    }

    public enum Type {
        OQL,
        JSON
    }
}

