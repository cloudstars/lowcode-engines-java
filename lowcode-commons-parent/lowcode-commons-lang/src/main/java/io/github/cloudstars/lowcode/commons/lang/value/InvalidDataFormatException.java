package io.github.cloudstars.lowcode.commons.lang.value;

/**
 * 非法的数据格式异常
 *
 * @author clouds
 */
public class InvalidDataFormatException extends RuntimeException {

    public InvalidDataFormatException() {
        super();
    }

    public InvalidDataFormatException(String message) {
        super(message);
    }

    public InvalidDataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataFormatException(Throwable cause) {
        super(cause);
    }

    protected InvalidDataFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
