package io.github.cloudstars.lowcode.bpm.engine.test;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;

/**
 * 流程配置工具类
 *
 * @author clouds
 */
public class ProcessConfigUtils {

    /**
     * 加载简单的流程
     *
     * @return 流程配置
     */
    public static ProcessConfig loadSimpleConfig() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("process/simple1.json");
        ProcessConfig config = new ProcessConfig(configJson);
        return config;
    }

    /**
     * 加载简单带分支的流程
     *
     * @return 流程配置
     */
    public static ProcessConfig loadSimpleBranchConfig() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("process/simple-branch.json");
        ProcessConfig config = new ProcessConfig(configJson);
        return config;
    }

}
