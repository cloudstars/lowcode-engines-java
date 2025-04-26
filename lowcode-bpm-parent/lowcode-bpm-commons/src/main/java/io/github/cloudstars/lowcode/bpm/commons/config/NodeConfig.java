package io.github.cloudstars.lowcode.bpm.commons.config;

import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.config.XConfig;

/**
 * 节点类型配置接口
 *
 * @author clouds
 */
public interface NodeConfig extends XConfig {

    /**
     * 获取节点的类型（SDK定义）
     *
     * @return
     */
    NodeTypeEnum getNodeType();

    /**
     * 获取节点的子类型（用户定义）
     *
     * @return
     */
    String getType();

    /**
     * 获取节点编号
     *
     * @return 节点编号
     */
    String getKey();

    /**
     * 获取节点名称
     *
     * @return 节点名称
     */
    String getName();

    /**
     * 接受一个BPM节点访问器
     *
     * @param visitor BPM节点访问器
     */
    void accept(BpmNodeVisitor visitor);

}
