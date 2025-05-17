package io.github.cloudstars.lowcode.object.form.editor;

/**
 * 表单视图的字段
 *
 * @author clouds
 */
public class FormViewField {

    private String fieldKey;

    private FormFieldMode mode;

    /**
     * 表单项操作模式
     *
     * @author clouds
     */
    public enum FormFieldMode {

        EDIT,
        READ;

    }
}
