package io.github.cloudstars.lowcode.object.view.config;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的视图操作配置
 *
 * @author clouds
 */
public abstract class AbstractViewActionConfig extends AbstractConfig {

    /**
     * 操作的名称
     */
    private String name;

    public AbstractViewActionConfig() {
    }

    public AbstractViewActionConfig(JsonObject configJson) {
        super(configJson);
    }

}
