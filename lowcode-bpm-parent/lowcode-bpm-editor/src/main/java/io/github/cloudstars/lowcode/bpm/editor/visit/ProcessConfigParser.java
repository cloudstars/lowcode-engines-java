package io.github.cloudstars.lowcode.bpm.editor.visit;

import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.commons.editor.XConfigParser;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;
import io.github.cloudstars.lowcode.commons.utils.json.JsonUtils;

/**
 * 流程配置解析器
 *
 * @author clouds
 */
public class ProcessConfigParser implements XConfigParser<ProcessConfig> {

    @Override
    public ProcessConfig fromJsonString(String jsonString) {
        ProcessConfig config = new ProcessConfig();
        JsonObject configJson = JsonUtils.parseObject(jsonString);
        config.setKey((String) configJson.get("key"));
        config.setCode((String) configJson.get("code"));
        config.setName((String) configJson.get("name"));

        return config;
    }

}
