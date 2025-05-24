package io.github.cloudstars.lowcode.commons.predicate;

import io.github.cloudstars.lowcode.predicate.type.JsonPredicateConfig;

/**
 * JSON断言
 *
 * @author clouds 
 */
public class FormulaPredicate extends AbstractPredicate<JsonPredicateConfig> {

    public FormulaPredicate(JsonPredicateConfig config) {
        super(config);
    }

    @Override
    public boolean test() {
        return false;
    }

}
