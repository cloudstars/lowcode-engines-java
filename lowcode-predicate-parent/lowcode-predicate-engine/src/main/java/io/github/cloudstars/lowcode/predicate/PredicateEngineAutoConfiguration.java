package io.github.cloudstars.lowcode.predicate;

import io.github.cloudstars.lowcode.predicate.engine.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class PredicateEngineAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        PredicateClassFactory.register(FormulaPredicate.class);
        PredicateClassFactory.register(JsonPredicate.class);
    }

}
