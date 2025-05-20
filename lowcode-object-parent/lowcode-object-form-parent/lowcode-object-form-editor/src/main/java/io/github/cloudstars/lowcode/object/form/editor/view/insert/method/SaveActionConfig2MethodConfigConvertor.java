package io.github.cloudstars.lowcode.object.form.editor.view.insert.method;

import io.github.cloudstars.lowcode.object.commons.XObjectConfigResolver;
import io.github.cloudstars.lowcode.object.form.editor.view.insert.ObjectInsertFormViewConfig;
import io.github.cloudstars.lowcode.object.form.editor.view.insert.action.SaveActionConfig;
import io.github.cloudstars.lowcode.object.method.editor.ObjectInsertOneMethodConfig;
import io.github.cloudstars.lowcode.object.view.editor.convert.AbstractObjectViewConfig2MethodConfigConvertor;

/**
 * 保存操作到数据插入模型接口的转换器
 *
 * @author clouds
 */
public class SaveActionConfig2MethodConfigConvertor extends AbstractObjectViewConfig2MethodConfigConvertor<ObjectInsertFormViewConfig, SaveActionConfig, ObjectInsertOneMethodConfig> {

    public SaveActionConfig2MethodConfigConvertor(XObjectConfigResolver resolver) {
        super(resolver);
    }

    @Override
    public ObjectInsertOneMethodConfig convert(ObjectInsertFormViewConfig viewConfig, SaveActionConfig actionConfig) {
        return null;
    }

}
