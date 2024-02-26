package net.cf.form.engine.object;

import net.cf.form.engine.exception.DataValidationException;

/**
 * 数据校验器
 *
 * @author clouds
 */
public interface XDataValidator {

    /**
     * 校验数据
     *
     * @param value 当前字段的数据
     * @param field 记录信息
     * @throws DataValidationException
     */
    void validate(Object value, XField field) throws DataValidationException;
}
