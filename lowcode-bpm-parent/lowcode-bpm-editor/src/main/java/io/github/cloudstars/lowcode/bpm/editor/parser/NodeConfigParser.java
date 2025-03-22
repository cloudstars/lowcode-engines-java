package io.github.cloudstars.lowcode.bpm.editor.parser;

import io.github.cloudstars.lowcode.bpm.editor.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;
import io.github.cloudstars.lowcode.commons.editor.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 通用节点配置解析器
 *
 * @author clouds
 */
public class NodeConfigParser implements XConfigParser<AbstractNodeConfig> {

    @Override
    public AbstractNodeConfig fromJson(JsonObject nodeConfigJson) {
        AbstractNodeConfig nodeConfig = null;
        NodeType nodeType = this.parseNodeType(nodeConfigJson);
        AbstractNodeConfigParser<? extends AbstractNodeConfig> nodeConfigParser = null;
        switch (nodeType) {
            case START:
                nodeConfigParser = new StartNodeConfigParser();
                break;
            case END:
                nodeConfigParser = new EndNodeConfigParser();
                break;
            case USER_APPROVE:
                nodeConfigParser = new UserApproveNodeConfigParser();
                break;
            case USER_WRITE:
                nodeConfigParser = new UserWriteNodeConfigParser();
                break;
            default:
                break;
        }

        if (nodeConfigParser != null) {
            nodeConfig = nodeConfigParser.fromJson(nodeConfigJson);
        }

        return nodeConfig;
    }

    /**
     * 解析节点的类型
     *
     * @param nodeConfigJson
     * @return
     */
    private NodeType parseNodeType(JsonObject nodeConfigJson) {
        Object typeValue = nodeConfigJson.get("type");
        if (typeValue == null) {
            throw new RuntimeException("节点类型信息[type]不存在，请检查：" + nodeConfigJson);
        }

        NodeType nodeType = NodeType.valueOf(typeValue.toString());
        if (nodeType == null) {
            throw new RuntimeException("节点类型时不正确，请检查：" + typeValue);
        }

        return nodeType;
    }

}
