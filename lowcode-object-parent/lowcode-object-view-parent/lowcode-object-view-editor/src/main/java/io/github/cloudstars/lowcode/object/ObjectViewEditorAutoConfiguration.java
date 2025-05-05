package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClassFactory;
import io.github.cloudstars.lowcode.object.view.editor.form.ObjectInsertFormViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.form.ObjectUpdateFormViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.form.ObjectViewFormViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.table.ObjectCrudTableViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.table.ObjectDataTableViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.table.ObjectLookupTableViewConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectViewEditorAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectViewConfigClassFactory.register(ObjectInsertFormViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectUpdateFormViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectViewFormViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectCrudTableViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectLookupTableViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectDataTableViewConfig.class);
    }

}
