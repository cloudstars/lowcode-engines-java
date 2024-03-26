package net.cf.form.repository.mongo.data;

/**
 * 处理expr解析时，内部的上下文信息
 */
public class InnerContext {
    // 是否关联了自增字段
    private boolean autoGen = false;
    // 是否为打tag字段
    private boolean fieldTag = false;

    public InnerContext() {

    }

    public static InnerContext getDefaultContextInfo() {
        return new InnerContext();
    }

    public static InnerContext getFieldTagContext() {
        InnerContext contextInfo = new InnerContext();
        contextInfo.fieldTag = true;
        return contextInfo;
    }

    public static InnerContext getAutoGenContext() {
        InnerContext contextInfo = new InnerContext();
        contextInfo.autoGen = true;
        return contextInfo;
    }

    public boolean isAutoGen() {
        return autoGen;
    }

    public void setAutoGen(boolean autoGen) {
        this.autoGen = autoGen;
    }

    public boolean isFieldTag() {
        return fieldTag;
    }

    public void setFieldTag(boolean fieldTag) {
        this.fieldTag = fieldTag;
    }
}
