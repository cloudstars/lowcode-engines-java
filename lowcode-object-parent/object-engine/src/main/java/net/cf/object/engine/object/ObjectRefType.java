package net.cf.object.engine.object;

/**
 * 模型引用的类型
 *
 * @author clouds
 */
public enum ObjectRefType {
    DETAIL, /* 子表引用 */
    MASTER, /* 主表引用 */
    LOOKUP; /* 查找表引用 */
}
