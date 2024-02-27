package net.cf.object.engine.def.field;

/**
 * 模型引用的类型
 *
 * @author clouds
 */
public enum ObjectRefType {
    REF_DETAIL, /* 子表引用 */
    REF_MASTER, /* 主表引用 */
    REF_LOOKUP; /* 相关表引用 */
}
