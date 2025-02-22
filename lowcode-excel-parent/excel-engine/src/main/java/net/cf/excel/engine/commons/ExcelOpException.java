package net.cf.excel.engine.commons;

/**
 * @Author: 胡凌云
 * @CreateTime: 2024-02-28 10:22
 * @Description: excel操作异常类
 */
public class ExcelOpException extends RuntimeException {
    public ExcelOpException() {
        super();
    }

    public ExcelOpException(String message) {
        super(message);
    }

    public ExcelOpException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelOpException(Throwable cause) {
        super(cause);
    }

    protected ExcelOpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}