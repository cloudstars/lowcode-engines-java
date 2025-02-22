package net.cf.form.repository.sql.parser;

/**
 * 解析异常
 *
 * @author clouds
 */
public class SqlParseException extends RuntimeException {

    public SqlParseException() {
    }

    public SqlParseException(String message) {
        super(message);
    }

    public SqlParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlParseException(Throwable cause) {
        super(cause);
    }
}
