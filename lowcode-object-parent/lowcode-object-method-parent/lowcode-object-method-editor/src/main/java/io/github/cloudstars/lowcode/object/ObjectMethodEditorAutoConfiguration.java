package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.method.editor.ObjectInsertOneMethodConfig;
import io.github.cloudstars.lowcode.object.method.editor.ObjectMethodConfigClassFactory;
import io.github.cloudstars.lowcode.object.method.editor.ObjectUpdateOneMethodConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectMethodEditorAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectMethodConfigClassFactory.register(ObjectInsertOneMethodConfig.class);
        ObjectMethodConfigClassFactory.register(ObjectUpdateOneMethodConfig.class);
    }

}
