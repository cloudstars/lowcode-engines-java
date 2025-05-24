package io.github.cloudstars.lowcode.predicate.engine;

import io.github.cloudstars.lowcode.predicate.type.JsonPredicateConfig;

import java.util.Map;

/**
 * JSON断言
 *
 * @author clouds
 */
public class JsonPredicate extends AbstractPredicate<JsonPredicateConfig> {

    public JsonPredicate(JsonPredicateConfig config) {
        super(config);
    }

    @Override
    public boolean test(Map<String, Object> paramMap) {
        return false;
    }

}
