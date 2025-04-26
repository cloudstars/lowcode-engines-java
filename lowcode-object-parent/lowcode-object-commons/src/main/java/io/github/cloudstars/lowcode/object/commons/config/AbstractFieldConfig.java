package io.github.cloudstars.lowcode.object.commons.config;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public class AbstractFieldConfig extends AbstractTypedConfig {

    // 字段编号配置名称
    private static final String ATTR_KEY = "key";
    // 字段名配置名称
    private static final String ATTR_NAME = "name";
    // 字段标签配置名称
    private static final String ATTR_LABEL = "label";

    /**
     * 字段的编号
     */
    private String key;

    /**
     * 字段的名称
     */
    private String name;

    /**
     * 字段的标题
     */
    private String label;


    public AbstractFieldConfig() {
    }

    public AbstractFieldConfig(JsonObject configJson) {
        super(configJson);

        this.key = (String) configJson.get(ATTR_KEY);
        this.name = (String) configJson.get(ATTR_NAME);
        this.label = (String) configJson.get(ATTR_LABEL);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_KEY, this.key);
        configJson.put(ATTR_NAME, this.name);
        configJson.put(ATTR_LABEL, this.label);

        return configJson;
    }

}
