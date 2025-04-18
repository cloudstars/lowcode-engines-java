package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.data.value.ValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.value.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * API配置
 *
 * @author clouds
 */
public class ApiConfig extends AbstractConfig implements XConfig {

    /**
     * 入参数据格式
     */
    private ValueTypeConfig input;

    /**
     * 出参数据格式
     */
    private ValueTypeConfig output;

    public ApiConfig() {
    }

    public ApiConfig(JsonObject configJson) {
        super(configJson);

        JsonObject inputJson = (JsonObject) configJson.get("input");
        this.setInput(ValueTypeConfigFactory.newInstance(inputJson));
        JsonObject outputJson = (JsonObject) configJson.get("output");
        if (outputJson != null) {
            this.setOutput(ValueTypeConfigFactory.newInstance(outputJson));
        }
    }

    public ApiConfig(ValueTypeConfig input, ValueTypeConfig output) {
        this.input = input;
        this.output = output;
    }

    public ValueTypeConfig getInput() {
        return input;
    }

    public void setInput(ValueTypeConfig input) {
        this.input = input;
    }

    public ValueTypeConfig getOutput() {
        return output;
    }

    public void setOutput(ValueTypeConfig output) {
        this.output = output;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = new JsonObject<>();
        configJson.put("input", input.toJson());
        configJson.put("output", output.toJson());
        return configJson;
    }

}
