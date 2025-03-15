package io.github.cloudstars.lowcode.bpm.editor.config;

import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 结束节点定义
 *
 * @author clouds
 */
public class EndNodeConfig extends AbstractNodeConfig {

    @Override
    public NodeType getType() {
        return NodeType.END;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();
        //
        return jsonObject;
    }

}
