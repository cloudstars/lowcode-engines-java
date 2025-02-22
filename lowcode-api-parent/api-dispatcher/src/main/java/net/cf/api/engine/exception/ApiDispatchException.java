package net.cf.api.engine.exception;

/**
 * api-sdk异常，非强制捕获类型
 *
 * @author clouds
 */
public class ApiDispatchException extends RuntimeException {

    public ApiDispatchException() {
        super();
    }

    public ApiDispatchException(String message) {
        super(message);
    }

    public ApiDispatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiDispatchException(Throwable cause) {
        super(cause);
    }

    protected ApiDispatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
