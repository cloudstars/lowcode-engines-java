package net.cf.formula.engine.parser;

/**
 * 解析异常
 *
 * @author clouds
 */
public class ParserException extends RuntimeException {

    public ParserException() {
    }

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserException(Throwable cause) {
        super(cause);
    }
}
