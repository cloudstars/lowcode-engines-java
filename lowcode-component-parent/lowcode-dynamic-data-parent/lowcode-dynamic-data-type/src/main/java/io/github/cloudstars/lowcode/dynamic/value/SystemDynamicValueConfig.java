package io.github.cloudstars.lowcode.dynamic.value;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 系统动态值配置
 *
 * @author clouds
 */
@DynamicValueConfigClass(name = "SYSTEM")
public class SystemDynamicValueConfig extends AbstractDynamicValueConfig {

    // 系统变量配置名称
    private static final String ATTR_NAME = "name";

    /**
     * 变量值
     */
    private String name;

    public SystemDynamicValueConfig() {
    }

    public SystemDynamicValueConfig(JsonObject configJson) {
        super(configJson);

        this.name = (String) configJson.get(ATTR_NAME);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_NAME, this.name);

        return configJson;
    }

}
