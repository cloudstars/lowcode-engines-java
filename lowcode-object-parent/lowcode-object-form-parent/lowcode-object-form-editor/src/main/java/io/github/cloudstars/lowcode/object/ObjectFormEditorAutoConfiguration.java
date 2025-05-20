package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.form.editor.view.update.ObjectUpdateFormViewConfig;
import io.github.cloudstars.lowcode.object.form.editor.view.read.ObjectReadFormViewConfig;
import io.github.cloudstars.lowcode.object.form.editor.view.insert.ObjectInsertFormViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.ObjectViewConfigClassFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectFormEditorAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ObjectViewConfigClassFactory.register(ObjectInsertFormViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectUpdateFormViewConfig.class);
        ObjectViewConfigClassFactory.register(ObjectReadFormViewConfig.class);

        // 注册内置的字段配置到组件配置的转换器
    }

}
