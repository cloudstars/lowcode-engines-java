package net.cf.form.engine.repository.oql;

@Deprecated
public class FastOqlException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FastOqlException() {
    }

    public FastOqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public FastOqlException(String message) {
        super(message);
    }

    public FastOqlException(Throwable cause) {
        super(cause);
    }
}
