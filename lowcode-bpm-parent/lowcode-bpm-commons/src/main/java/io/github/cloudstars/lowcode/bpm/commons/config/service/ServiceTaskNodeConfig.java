package io.github.cloudstars.lowcode.bpm.commons.config.service;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigClass;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeTypeEnum;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 程序节点配置
 *
 * @author clouds
 */
@NodeConfigClass(type = "DEFAULT.SERVICE")
public class ServiceTaskNodeConfig extends AbstractNodeConfig {

    /**
     * 程序的类名
     */
    private String className;

    public ServiceTaskNodeConfig() {
    }

    public ServiceTaskNodeConfig(JsonObject configJson) {
        super(configJson);

        this.className = (String) configJson.get("className");
    }


    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.SERVICE;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();
        jsonObject.put("className", this.className);

        return jsonObject;
    }

}
