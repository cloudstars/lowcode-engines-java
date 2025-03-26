package io.github.cloudstars.lowcode.object.view;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的视图配置类型
 *
 * @author clouds
 */
public abstract class AbstractViewConfig implements XConfig {

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        return jsonObject;
    }

}
