package io.github.cloudstars.lowcode.bpm.engine.provider;

import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;

import java.util.Map;

/**
 * Bpm提供方接口
 *
 * @author clouds
 */
public interface BpmProvider {

    /**
     * 部署一个流程
     *
     * @param config 流程定义
     * @return 流程部署ID
     */
    String deploy(ProcessConfig config);

    /**
     * 启动指定流程
     *
     * @param processKey 流程编号
     * @param dataMap 流程变量
     * @return 流程实例ID
     */
    String start(String processKey, Map<String, Object> dataMap);

}
