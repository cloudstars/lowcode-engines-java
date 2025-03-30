
package io.github.cloudstars.lowcode.bpm.commons.config.user;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象流程用户节点配置
 *
 * @author clouds
 */
public abstract class AbstractUserNodeConfig extends AbstractNodeConfig {

    public AbstractUserNodeConfig() {
    }

    public AbstractUserNodeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();

        return jsonObject;
    }

}
