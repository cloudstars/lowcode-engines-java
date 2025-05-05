package io.github.cloudstars.lowcode.object.view.editor;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的视图操作配置
 *
 * @author clouds
 */
public abstract class AbstractObjectViewActionConfig extends AbstractTypedConfig {

    /**
     * 操作的名称
     */
    private String name;

    public AbstractObjectViewActionConfig() {
    }

    public AbstractObjectViewActionConfig(JsonObject configJson) {
        super(configJson);
    }

}
