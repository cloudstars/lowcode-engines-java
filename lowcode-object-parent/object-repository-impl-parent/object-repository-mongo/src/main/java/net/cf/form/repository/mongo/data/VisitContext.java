package net.cf.form.repository.mongo.data;

/**
 * 处理expr解析时，上下文信息
 */
public class VisitContext {
    // 是否关联了自增字段
    private boolean autoGen = false;
    // 是否为打tag字段
    private boolean fieldTag = false;

    public VisitContext() {

    }

    public static VisitContext getDefaultContextInfo() {
        return new VisitContext();
    }

    public static VisitContext getFieldTagContext() {
        VisitContext contextInfo = new VisitContext();
        contextInfo.fieldTag = true;
        return contextInfo;
    }

    public static VisitContext getAutoGenContext() {
        VisitContext contextInfo = new VisitContext();
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
