package io.github.cloudstars.lowcode.object.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;

import java.util.ArrayList;

/**
 * 模型解析器
 *
 * @author clouds
 */
public final class ObjectConfigParser {

    /**
     * 解析一个模型的配置
     *
     * @param jsonConfig
     * @return
     */
    public static ObjectConfig parse(String jsonConfig) {
        ObjectConfig config = new ObjectConfig();
        JsonObject configJson = JsonUtils.toJsonObject(jsonConfig);
        config.setKey((String) configJson.get("key"));
        config.setCode((String) configJson.get("code"));
        config.setName((String) configJson.get("name"));
        config.setFields(new ArrayList<>());
        return config;
    }

}
