package net.cf.object.engine.object;

/**
 * 模型引用的类型
 *
 * @author clouds
 */
public enum ObjectRefType {
    MASTER, /* 主模型引用，1对1 */
    DETAIL, /* 子模型引用，1对多 */
    LOOKUP; /* 查找模型引用，1对1或1对多 */
}
