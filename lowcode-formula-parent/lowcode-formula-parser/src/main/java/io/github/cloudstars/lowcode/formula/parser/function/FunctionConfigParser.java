package io.github.cloudstars.lowcode.formula.parser.function;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.XConfigDeserializer;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置对象解析器
 *
 * @author clouds
 */
public class FunctionConfigParser implements XConfigDeserializer<FunctionConfig> {

    @Override
    public FunctionConfig parse(JsonObject configJson) {
        FunctionConfig config = new FunctionConfig();
        config.setName(ConfigUtils.getRequiredString(configJson, "name"));
        ConfigUtils.<JsonArray>consumeIfPresent(configJson, "params", (v) -> {
            List<FunctionParam> params = new ArrayList<>();
            for (Object p : v) {
                FunctionParam param = this.parseParam((JsonObject) p);
                params.add(param);
            }
            config.setParams(params);
        });

        return config;
    }

    /**
     * 解析函数参数
     *
     * @param configJson 配置Json对象
     * @return 函数参数
     */
    public FunctionParam parseParam(JsonObject configJson) {
        FunctionParam param = new FunctionParam();
        param.setName(ConfigUtils.getRequiredString(configJson, "name"));
        param.setRequired(ConfigUtils.getBoolean(configJson, "required"));
        ConfigUtils.<JsonObject>consumeIfPresent(configJson, "valueType", (v) -> {
            XValueTypeConfig valueType = ValueTypeConfigFactory.newInstance(v);
            param.setValueTypeConfig(valueType);
        });

        return param;
    }

}
