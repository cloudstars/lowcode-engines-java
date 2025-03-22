
package io.github.cloudstars.lowcode.bpm.editor.config.user;

import io.github.cloudstars.lowcode.bpm.editor.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象流程用户节点定义
 *
 * @author clouds
 */
public abstract class AbstractUserNodeConfig extends AbstractNodeConfig {

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();

        return jsonObject;
    }

}
