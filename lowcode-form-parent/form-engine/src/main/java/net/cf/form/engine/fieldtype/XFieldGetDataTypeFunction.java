package net.cf.form.engine.fieldtype;

import net.cf.form.engine.field.XFieldImpl;
import net.cf.form.engine.repository.data.DataType;

/**
 * 字段的格式化函数
 *
 * @author clouds
 */
@Deprecated
@FunctionalInterface
public interface XFieldGetDataTypeFunction {

    String NAME = "getDataType";

    DataType dataType(XFieldImpl field);
}
