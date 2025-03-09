package net.cf.excel.provider.exception;

public class ExcelWriteException extends RuntimeException {

    public ExcelWriteException() {
        super();
    }

    public ExcelWriteException(String message) {
        super(message);
    }

    public ExcelWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelWriteException(Throwable cause) {
        super(cause);
    }

    protected ExcelWriteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
