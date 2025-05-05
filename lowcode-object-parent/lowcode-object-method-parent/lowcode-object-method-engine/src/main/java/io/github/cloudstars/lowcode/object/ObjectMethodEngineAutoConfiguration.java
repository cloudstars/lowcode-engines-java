package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.method.engine.ObjectInsertOneMethod;
import io.github.cloudstars.lowcode.object.method.engine.ObjectMethodClassFactory;
import io.github.cloudstars.lowcode.object.method.engine.ObjectUpdateOneMethod;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectMethodEngineAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMethodClassFactory.register(ObjectInsertOneMethod.class);
        ObjectMethodClassFactory.register(ObjectUpdateOneMethod.class);
    }

}
