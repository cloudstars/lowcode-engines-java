package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.commons.lang.exception.ProgramException;

/**
 * 语法错误
 *
 * @author clouds
 */
public class SyntaxException extends ProgramException {

    public SyntaxException() {
        super();
    }

    public SyntaxException(String message) {
        super(message);
    }

    public SyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public SyntaxException(Throwable cause) {
        super(cause);
    }

    public SyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
