package io.github.cloudstars.lowcode.bpm.vendor;

import java.util.Map;

/**
 * Bpm流程提供方接口
 *
 * @author clouds
 */
public interface BpmProcessProvider {

    /**
     * 启动指定流程
     *
     * @param processKey 流程编号
     * @param dataMap 流程变量
     * @return 流程实例ID
     */
    String start(String processKey, Map<String, Object> dataMap);

}
