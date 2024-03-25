package net.cf.form.repository.mongo.data;

/**
 * 处理expr解析时，内部的上下文信息
 */
public class InnerContextInfo {
    // 是否关联了自增字段
    private boolean autoGen = false;
    // 是否为打tag字段
    private boolean fieldTag = false;

    public InnerContextInfo() {

    }

    public static InnerContextInfo getDefaultContextInfo() {
        return new InnerContextInfo();
    }

    public static InnerContextInfo getFieldTagContext() {
        InnerContextInfo contextInfo = new InnerContextInfo();
        contextInfo.fieldTag = true;
        return contextInfo;
    }

    public static InnerContextInfo getAutoGenContext() {
        InnerContextInfo contextInfo = new InnerContextInfo();
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
