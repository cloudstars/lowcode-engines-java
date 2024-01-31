package net.cf.form.engine.fieldtype;

/**
 * 字段的格式化函数
 *
 * @author clouds
 */
@Deprecated
@FunctionalInterface
public interface XFieldFormatterFunction {

    String NAME = "formatter";

    Object format(Object data);
}
