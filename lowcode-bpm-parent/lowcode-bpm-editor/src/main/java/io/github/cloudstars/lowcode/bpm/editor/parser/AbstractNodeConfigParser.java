package io.github.cloudstars.lowcode.bpm.editor.parser;

import io.github.cloudstars.lowcode.bpm.editor.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.commons.editor.XConfigParser;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 抽象的流程节点配置解析器
 *
 * @author clouds
 */
public abstract class AbstractNodeConfigParser<T extends AbstractNodeConfig> implements XConfigParser<T> {

    /**
     * 初始化结点基础信息
     *
     * @param nodeConfig
     * @param nodeConfigJson
     */
    protected void initCommonNode(AbstractNodeConfig nodeConfig, JsonObject nodeConfigJson) {
        nodeConfig.setKey(nodeConfigJson.get("key").toString());
        nodeConfig.setName(nodeConfigJson.get("name").toString());
    }

}
