package io.github.cloudstars.lowcode.bpm.engine.provider;

import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;

/**
 * Bpm引擎提供方接口
 *
 * @author clouds
 */
public interface EngineProvider {

    /**
     * 部署一个流程
     *
     * @param config 流程定义
     * @return 流程部署ID
     */
    String deploy(ProcessConfig config);

}
