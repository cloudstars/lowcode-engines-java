package io.github.cloudstars.lowcode.formula.parser.function;

import io.github.cloudstars.lowcode.commons.lang.exception.ProgramException;

/**
 * 函数找不到错误
 *
 * @author clouds
 */
public class FunctionException extends ProgramException {

    public FunctionException() {
        super();
    }

    public FunctionException(String message) {
        super(message);
    }

    public FunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionException(Throwable cause) {
        super(cause);
    }

    public FunctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
