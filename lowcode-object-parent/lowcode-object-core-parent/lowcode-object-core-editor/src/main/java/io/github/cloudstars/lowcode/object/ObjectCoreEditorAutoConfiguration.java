package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.core.editor.FormBasedObjectConfig;
import io.github.cloudstars.lowcode.object.core.editor.ObjectConfigClassFactory;
import io.github.cloudstars.lowcode.object.core.editor.field.JsonObjectFieldConfig;
import io.github.cloudstars.lowcode.object.core.editor.field.NumberObjectFieldConfig;
import io.github.cloudstars.lowcode.object.core.editor.field.ObjectFieldConfigClassFactory;
import io.github.cloudstars.lowcode.object.core.editor.field.TextObjectFieldConfig;
import io.github.cloudstars.lowcode.object.core.editor.field.objectref.DetailObjectObjectFieldConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectCoreEditorAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的模型配置类型
        ObjectConfigClassFactory.register(FormBasedObjectConfig.class);

        // 注册内置的字段配置类型
        ObjectFieldConfigClassFactory.register(TextObjectFieldConfig.class);
        ObjectFieldConfigClassFactory.register(NumberObjectFieldConfig.class);
        ObjectFieldConfigClassFactory.register(JsonObjectFieldConfig.class);
        ObjectFieldConfigClassFactory.register(DetailObjectObjectFieldConfig.class);
    }

}
