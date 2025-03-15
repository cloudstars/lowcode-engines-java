package io.github.cloudstars.lowcode.bpm.editor.parser;

import io.github.cloudstars.lowcode.bpm.editor.config.*;
import io.github.cloudstars.lowcode.bpm.editor.config.user.UserApproveNodeConfig;
import io.github.cloudstars.lowcode.bpm.editor.config.user.UserWriteNodeConfig;
import io.github.cloudstars.lowcode.commons.editor.XConfigParser;
import io.github.cloudstars.lowcode.commons.utils.json.JsonArray;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;
import io.github.cloudstars.lowcode.commons.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程配置解析器
 *
 * @author clouds
 */
public class ProcessConfigParser implements XConfigParser<ProcessConfig> {

    @Override
    public ProcessConfig fromJsonString(String jsonString) {
        ProcessConfig processConfig = new ProcessConfig();
        JsonObject configJson = JsonUtils.parseObject(jsonString);
        processConfig.setKey((String) configJson.get("key"));
        processConfig.setCode((String) configJson.get("code"));
        processConfig.setName((String) configJson.get("name"));
        Object nodesValue = configJson.get("nodes");
        if (nodesValue == null || !(nodesValue instanceof JsonArray)) {
            throw new RuntimeException("节点信息nodes不存在或不是数组格式，请检查：" + jsonString);
        }

        List<AbstractNodeConfig> nodeConfigs = new ArrayList<>();
        processConfig.setNodes(nodeConfigs);

        JsonArray nodes = (JsonArray) nodesValue;
        nodes.forEach((node) -> {
            JsonObject jsonNode = (JsonObject) node;
            AbstractNodeConfig nodeConfig = null;
            NodeType nodeType = this.parseNodeType(node);
            switch (nodeType) {
                case START:
                    nodeConfig = this.buildStartNode(jsonNode);
                    break;
                case END:
                    nodeConfig = this.buildEndNode(jsonNode);
                    break;
                case USER_WRITE:
                    nodeConfig = this.buildUserWriteNode(jsonNode);
                    break;
                case USER_APPROVE:
                    nodeConfig = this.buildUserApproveNode(jsonNode);
                    break;
                default:
                    break;
            }
            if (nodeConfig != null) {
                nodeConfigs.add(nodeConfig);
            }
        });

        return processConfig;
    }

    /**
     * 解析节点的类型
     *
     * @param node
     * @return
     */
    private NodeType parseNodeType(Object node) {
        if (!(node instanceof JsonObject)) {
            throw new RuntimeException("节点信息中必须是一个JSON对象，请检查：" + JsonUtils.toJsonString(node));
        }

        JsonObject itemJson = (JsonObject) node;
        Object typeValue = itemJson.get("type");
        if (typeValue == null) {
            throw new RuntimeException("节点信息中必须包含type属性，请检查：" + JsonUtils.toJsonString(node));
        }
        NodeType nodeType = NodeType.valueOf(typeValue.toString());
        if (nodeType == null) {
            throw new RuntimeException("节点类型时不正确，请检查：" + typeValue);
        }

        return nodeType;
    }

    /**
     * 构建开始结点
     *
     * @param jsonNode 节点信息
     */
    private StartNodeConfig buildStartNode(JsonObject jsonNode) {
        StartNodeConfig nodeConfig = new StartNodeConfig();
        this.initCommonNode(nodeConfig, jsonNode);
        return nodeConfig;
    }


    /**
     * 构建结束结点
     *
     * @param jsonNode 节点信息
     */
    private EndNodeConfig buildEndNode(JsonObject jsonNode) {
        EndNodeConfig nodeConfig = new EndNodeConfig();
        this.initCommonNode(nodeConfig, jsonNode);
        return nodeConfig;
    }

    /**
     * 构建用户填写节点
     *
     * @param jsonNode 节点信息
     * @return 用户填写节点配置
     */
    private UserWriteNodeConfig buildUserWriteNode(JsonObject jsonNode) {
        UserWriteNodeConfig nodeConfig = new UserWriteNodeConfig();
        this.initCommonNode(nodeConfig, jsonNode);
        return nodeConfig;
    }

    /**
     * 构建用户审批节点
     *
     * @param jsonNode 节点信息
     * @return 用户审批节点配置
     */
    private UserApproveNodeConfig buildUserApproveNode(JsonObject jsonNode) {
        UserApproveNodeConfig nodeConfig = new UserApproveNodeConfig();
        this.initCommonNode(nodeConfig, jsonNode);
        return nodeConfig;
    }

    /**
     * 初始化结点基础信息
     *
     * @param nodeConfig
     * @param jsonNode
     */
    private void initCommonNode(AbstractNodeConfig nodeConfig, JsonObject jsonNode) {
        nodeConfig.setKey(jsonNode.get("key").toString());
        nodeConfig.setName(jsonNode.get("name").toString());
    }

}
