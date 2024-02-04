package net.cf.form.repository.sql;

public class FastSqlException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FastSqlException() {
    }

    public FastSqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public FastSqlException(String message) {
        super(message);
    }

    public FastSqlException(Throwable cause) {
        super(cause);
    }
}
