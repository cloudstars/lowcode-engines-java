package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.form.editor.ObjectUpdateFormViewConfig;
import io.github.cloudstars.lowcode.object.form.editor.ObjectViewFormViewConfig;
import io.github.cloudstars.lowcode.object.form.editor.view.insert.ObjectInsertFormViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClassFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectFormEditorAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectViewConfigClassFactory.register(ObjectInsertFormViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectUpdateFormViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectViewFormViewConfig.class);
    }

}
