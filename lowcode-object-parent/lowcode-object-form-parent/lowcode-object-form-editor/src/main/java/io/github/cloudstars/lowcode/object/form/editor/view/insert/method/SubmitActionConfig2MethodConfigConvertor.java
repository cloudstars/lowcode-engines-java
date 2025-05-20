package io.github.cloudstars.lowcode.object.form.editor.view.insert.method;

import io.github.cloudstars.lowcode.object.commons.XObjectConfigResolver;
import io.github.cloudstars.lowcode.object.form.editor.view.insert.ObjectInsertFormViewConfig;
import io.github.cloudstars.lowcode.object.form.editor.view.insert.action.SubmitActionConfig;
import io.github.cloudstars.lowcode.object.method.editor.ObjectInsertOneMethodConfig;
import io.github.cloudstars.lowcode.object.view.editor.convert.AbstractObjectViewConfig2MethodConfigConvertor;

/**
 * 提交操作到数据插入模型接口的转换器
 *
 * @author clouds 
 */
public class SubmitActionConfig2MethodConfigConvertor extends AbstractObjectViewConfig2MethodConfigConvertor<ObjectInsertFormViewConfig, SubmitActionConfig, ObjectInsertOneMethodConfig> {

    public SubmitActionConfig2MethodConfigConvertor(XObjectConfigResolver resolver) {
        super(resolver);
    }

    @Override
    public ObjectInsertOneMethodConfig convert(ObjectInsertFormViewConfig viewConfig, SubmitActionConfig actionConfig) {
        return null;
    }

}
