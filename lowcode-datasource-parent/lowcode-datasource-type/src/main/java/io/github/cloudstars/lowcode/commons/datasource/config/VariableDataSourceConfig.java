package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

/**
 * 变量数据源配置
 *
 * @param <V>
 */
@DataSourceConfigClass(name = "VARIABLE")
public class VariableDataSourceConfig<V extends XValueTypeConfig> extends AbstractDataSourceConfig<V> {

    // 变量名配置项名称
    private static final String ATTR_VARIABLE = "variable";

    /**
     * 变量名
     */
    private String variable;

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }


    public VariableDataSourceConfig() {
    }

    public VariableDataSourceConfig(JsonObject configJson) {
        super(configJson);

        this.variable = ConfigUtils.getRequiredString(configJson, ATTR_VARIABLE);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_VARIABLE, this.variable);

        return configJson;
    }

}
