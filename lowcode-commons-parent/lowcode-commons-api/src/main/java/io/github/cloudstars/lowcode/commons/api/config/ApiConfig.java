package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.data.type.DataTypeConfig;
import io.github.cloudstars.lowcode.commons.data.type.DataTypeConfigFactory;
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
    private DataTypeConfig input;

    /**
     * 出参数据格式
     */
    private DataTypeConfig output;

    public ApiConfig() {
    }

    public ApiConfig(JsonObject configJson) {
        super(configJson);

        JsonObject inputJson = (JsonObject) configJson.get("input");
        this.setInput(DataTypeConfigFactory.newInstance(inputJson));
        JsonObject outputJson = (JsonObject) configJson.get("output");
        if (outputJson != null) {
            this.setOutput(DataTypeConfigFactory.newInstance(outputJson));
        }
    }

    public ApiConfig(DataTypeConfig input, DataTypeConfig output) {
        this.input = input;
        this.output = output;
    }

    public DataTypeConfig getInput() {
        return input;
    }

    public void setInput(DataTypeConfig input) {
        this.input = input;
    }

    public DataTypeConfig getOutput() {
        return output;
    }

    public void setOutput(DataTypeConfig output) {
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
