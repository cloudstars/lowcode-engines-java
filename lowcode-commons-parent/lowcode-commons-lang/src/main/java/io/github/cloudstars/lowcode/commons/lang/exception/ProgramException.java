package io.github.cloudstars.lowcode.commons.lang.exception;

/**
 * 程序异常基类（程序员可见的异常）
 *
 * @author clouds
 */
public class ProgramException extends RuntimeException {

    public ProgramException() {
    }

    public ProgramException(String message) {
        super(message);
    }

    public ProgramException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProgramException(Throwable cause) {
        super(cause);
    }

    public ProgramException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
