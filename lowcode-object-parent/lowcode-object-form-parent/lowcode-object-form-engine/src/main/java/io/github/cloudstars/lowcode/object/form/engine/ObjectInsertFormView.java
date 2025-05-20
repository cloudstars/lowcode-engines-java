package io.github.cloudstars.lowcode.object.form.engine;

import io.github.cloudstars.lowcode.commons.lang.exception.SystemException;
import io.github.cloudstars.lowcode.object.form.editor.view.insert.ObjectInsertFormViewConfig;
import io.github.cloudstars.lowcode.object.method.editor.ObjectInsertOneMethodConfig;
import io.github.cloudstars.lowcode.object.method.engine.ObjectInsertOneMethod;
import io.github.cloudstars.lowcode.object.method.engine.XObjectMethod;

/**
 * 模型插入表单视图
 *
 * @author clouds
 */
public class ObjectInsertFormView extends AbstractObjectFormView<ObjectInsertFormViewConfig> {

    private static final String METHOD_SAVE = "SAVE";
    private static final String METHOD_SUBMIT = "SUBMIT";

    public ObjectInsertFormView(ObjectInsertFormViewConfig viewConfig) {
        super(viewConfig);
    }

    @Override
    protected XObjectMethod resolveMethod(String methodType) {
        XObjectMethod method = null;
        if (METHOD_SAVE.equalsIgnoreCase(methodType)) {
            method = this.buildSaveMethod();
        } else if (METHOD_SUBMIT.equalsIgnoreCase(methodType)) {
            // method = this.buildSubmitMethod();
        } else {
            throw new SystemException("表单插入视图不支持[" + methodType + "]方法");
        }

        return method;
    }

    /**
     * 构建模型单条插入方法
     *
     * @return 方法实现
     */
    private ObjectInsertOneMethod buildSaveMethod() {
        ObjectInsertOneMethodConfig methodConfig = this.buildSaveMethodConfig();
        ObjectInsertOneMethod method = new ObjectInsertOneMethod(methodConfig);
        // TODO
        return method;
    }

    /**
     * 构建模型单条插入方法的配置
     *
     * @return 方法配置
     */
    private ObjectInsertOneMethodConfig buildSaveMethodConfig() {
        ObjectInsertOneMethodConfig config = new ObjectInsertOneMethodConfig();
        // TODO
        return config;
    }


}
