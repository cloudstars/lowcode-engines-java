package net.cf.object.engine.object;

import net.cf.object.engine.exception.DataValidationException;

/**
 * 数据校验器
 *
 * @author clouds
 */
public interface XDataValidator<F extends XField> {

    /**
     * 校验数据
     *
     * @param value 当前字段的数据
     * @param field 字段信息
     * @throws DataValidationException
     */
    void validate(Object value, F field) throws DataValidationException;
}
