package io.github.cloudstars.lowcode.commons.data.predicate;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的表达式配置
 *
 * @author clouds
 */
public class AbstractExpressionConfig extends AbstractTypedConfig implements XExpressionConfig {

    public AbstractExpressionConfig() {
    }

    public AbstractExpressionConfig(JsonObject configJson) {
        super(configJson);
    }

}
