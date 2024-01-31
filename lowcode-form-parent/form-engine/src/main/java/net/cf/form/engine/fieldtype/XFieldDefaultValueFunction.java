package net.cf.form.engine.fieldtype;

/**
 * 字段的初始化函数
 *
 * @author clouds
 */
@Deprecated
@FunctionalInterface
public interface XFieldDefaultValueFunction {

    String NAME = "defaultValue";

    Object getValue(Object data);
}
