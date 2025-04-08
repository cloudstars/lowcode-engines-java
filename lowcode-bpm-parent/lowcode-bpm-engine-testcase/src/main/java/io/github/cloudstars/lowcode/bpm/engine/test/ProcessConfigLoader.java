package io.github.cloudstars.lowcode.bpm.engine.test;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;

/**
 * 流程配置工具类
 *
 * @author clouds
 */
public final class ProcessConfigLoader {

    private ProcessConfigLoader() {
    }

    /**
     * 加载简单的流程
     *
     * @return 流程配置
     */
    public static ProcessConfig loadFromClassPath(String classpath) {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath(classpath);
        ProcessConfig config = new ProcessConfig(configJson);
        return config;
    }

}
