package io.github.cloudstars.lowcode.commons.value.type.config.defaultvalue;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 系统默认值配置
 *
 * @author clouds 
 */
@DefaultValueConfigClass(name = "SYSTEM")
public class SystemDefaultValueConfig extends AbstractDefaultValueConfig {

    // 变量名配置名称
    private static final String ATTR_VARIABLE = "variable";

    // 属性名配置名称
    private static final String ATTR_PROPERTY = "property";

    private String variable;

    private String property;

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public SystemDefaultValueConfig() {
    }

    public SystemDefaultValueConfig(JsonObject configJson) {
        super(configJson);

        this.variable = (String) configJson.get(ATTR_VARIABLE);
        this.property = (String) configJson.get(ATTR_PROPERTY);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_VARIABLE, this.variable);
        configJson.putIfNotNull(ATTR_PROPERTY, this.property);

        return configJson;
    }

}
