package net.cf.form.engine.fieldtype;

/**
 * 字段的校验函数
 *
 * @author clouds
 */
@Deprecated
@FunctionalInterface
public interface XFieldValidatorFunction {

    String NAME = "validator";

    boolean validate(Object data);
}
