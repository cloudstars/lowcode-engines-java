package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * API配置
 *
 * @author clouds
 */
public class ApiConfig extends AbstractConfig {

    // 输入配置名称
    private static final String ATTR_INPUT = "input";

    // 输出配置名称
    private static final String ATTR_OUTPUT = "output";

    /**
     * 入参配置
     */
    private ApiInputConfig input;

    /**
     * 出参配置
     */
    private ApiOutputConfig output;

    public ApiConfig() {
    }

    public ApiConfig(ApiInputConfig inputConfig, ApiOutputConfig outputConfig) {
        this.input = inputConfig;
        this.output = outputConfig;
    }

    public ApiConfig(JsonObject configJson) {
        super(configJson);

        JsonObject inputConfigJson = (JsonObject) configJson.get(ATTR_INPUT);
        this.setInput(new ApiInputConfig(inputConfigJson));
        JsonObject outputConfigJson = (JsonObject) configJson.get(ATTR_OUTPUT);
        this.setOutput(new ApiOutputConfig(outputConfigJson));
    }

    public ApiInputConfig getInput() {
        return input;
    }

    public void setInput(ApiInputConfig input) {
        this.input = input;
    }

    public ApiOutputConfig getOutput() {
        return output;
    }

    public void setOutput(ApiOutputConfig output) {
        this.output = output;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_INPUT, input.toJson());
        configJson.put(ATTR_OUTPUT, output.toJson());

        return configJson;
    }

}
