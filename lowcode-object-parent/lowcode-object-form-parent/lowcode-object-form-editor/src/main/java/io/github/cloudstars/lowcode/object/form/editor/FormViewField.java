package io.github.cloudstars.lowcode.object.form.editor;

/**
 * 表单视图的字段
 *
 * @author clouds
 */
public class FormViewField {

    /**
     * 字段编号
     */
    private String fieldKey;

    /**
     * 操作权限
     */
    private OpMode mode;

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public OpMode getMode() {
        return mode;
    }

    public void setMode(OpMode mode) {
        this.mode = mode;
    }

    /**
     * 表单项操作权限
     *
     * @author clouds
     */
    public enum OpMode {

        EDIT, // 编辑
        READ; // 只读

    }
}
