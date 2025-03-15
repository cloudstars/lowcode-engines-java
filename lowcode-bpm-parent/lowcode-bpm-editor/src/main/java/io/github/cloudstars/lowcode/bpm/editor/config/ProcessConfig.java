package io.github.cloudstars.lowcode.bpm.editor.config;

import io.github.cloudstars.lowcode.commons.editor.XConfig;
import io.github.cloudstars.lowcode.commons.utils.json.JsonArray;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

import java.util.List;

/**
 * 流程定义
 *
 * @author clouds
 */
public class ProcessConfig implements XConfig {

    /**
     * 流程编号（自动生成）
     */
    private String key;

    /**
     * 流程代码（用户输入）
     */
    private String code;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 流程的节点
     */
    private List<? extends AbstractNodeConfig> nodes;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<? extends AbstractNodeConfig> getNodes() {
        return nodes;
    }

    public void setNodes(List<? extends AbstractNodeConfig> nodes) {
        this.nodes = nodes;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("key", this.key);
        jsonObject.put("code", this.code);
        jsonObject.put("name", this.name);

        JsonArray nodeJsonArray = new JsonArray();
        this.nodes.forEach(node -> {
            nodeJsonArray.add(node.toJson());
        });
        jsonObject.put("nodes", nodeJsonArray);

        return jsonObject;
    }

}
