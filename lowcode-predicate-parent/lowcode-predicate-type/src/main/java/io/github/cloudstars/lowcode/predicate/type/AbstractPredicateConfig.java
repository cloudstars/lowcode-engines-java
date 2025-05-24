package io.github.cloudstars.lowcode.predicate.type;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的断言配置
 *
 * @author clouds
 */
public class AbstractPredicateConfig extends AbstractTypedConfig implements XPredicateConfig {

    public AbstractPredicateConfig() {
    }

    public AbstractPredicateConfig(JsonObject configJson) {
        super(configJson);
    }

}
