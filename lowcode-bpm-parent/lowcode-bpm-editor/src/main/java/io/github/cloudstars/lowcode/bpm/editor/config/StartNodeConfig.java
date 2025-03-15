package io.github.cloudstars.lowcode.bpm.editor.config;

import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 开始节点定义
 *
 * @author clouds
 */
public class StartNodeConfig extends AbstractNodeConfig {


    @Override
    public NodeType getType() {
        return NodeType.START;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();
        //
        return jsonObject;
    }

}
