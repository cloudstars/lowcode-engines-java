package io.github.cloudstars.lowcode.commons.lang.value;

/**
 * 非法的数据格式异常
 *
 * @author clouds
 */
public class InvalidValueFormatException extends RuntimeException {

    public InvalidValueFormatException() {
        super();
    }

    public InvalidValueFormatException(String message) {
        super(message);
    }

    public InvalidValueFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValueFormatException(Throwable cause) {
        super(cause);
    }

    protected InvalidValueFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
