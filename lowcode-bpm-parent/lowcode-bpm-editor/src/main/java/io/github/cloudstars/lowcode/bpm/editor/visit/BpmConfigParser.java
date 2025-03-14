package io.github.cloudstars.lowcode.bpm.editor.visit;

import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.commons.lang.util.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.util.JsonUtils;

public class BpmConfigParser {

    public static ProcessConfig parse(String jsonConfig) {
        ProcessConfig config = new ProcessConfig();
        JsonObject configJson = JsonUtils.parseObject(jsonConfig);
        config.setKey((String) configJson.get("key"));
        config.setCode((String) configJson.get("code"));
        config.setName((String) configJson.get("name"));

        return config;
    }
}
