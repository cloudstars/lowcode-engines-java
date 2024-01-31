package net.cf.form.engine.fieldtype;

/**
 * 字段的反格式化函数
 *
 * @author clouds
 */
@Deprecated
@FunctionalInterface
public interface XFieldUnformatterFunction {

    String NAME = "unformatter";

    Object unformat(Object data);
}
