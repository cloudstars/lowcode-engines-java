package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.table.editor.ObjectCrudTableViewConfig;
import io.github.cloudstars.lowcode.object.table.editor.ObjectDataTableViewConfig;
import io.github.cloudstars.lowcode.object.table.editor.ObjectLookupTableViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClassFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectTableEditorAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectViewConfigClassFactory.register(ObjectCrudTableViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectLookupTableViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectDataTableViewConfig.class);
    }

}
