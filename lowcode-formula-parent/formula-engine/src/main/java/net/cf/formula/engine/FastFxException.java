package net.cf.formula.engine;

public class FastFxException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FastFxException() {
    }

    public FastFxException(String message, Throwable cause) {
        super(message, cause);
    }

    public FastFxException(String message) {
        super(message);
    }

    public FastFxException(Throwable cause) {
        super(cause);
    }
}
