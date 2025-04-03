package io.github.cloudstars.lowcode.bpm.commons.parser;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfigFactory;
import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 节点配置解析器
 *
 * @author clouds
 */
public class NodeConfigParser implements XConfigParser<AbstractNodeConfig> {

    @Override
    public AbstractNodeConfig fromJson(JsonObject configJson) {
        Object subTypeValue = configJson.get("subType");
        if (subTypeValue == null) {
            throw new RuntimeException("节点类型定义中subType不能为空，请检查您的配置：" + configJson);
        }

        return NodeConfigFactory.newInstance(configJson);
    }

}
