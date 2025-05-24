package io.github.cloudstars.lowcode.commons.predicate;

import io.github.cloudstars.lowcode.predicate.type.JsonPredicateConfig;

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
    public boolean test() {
        return false;
    }

}
