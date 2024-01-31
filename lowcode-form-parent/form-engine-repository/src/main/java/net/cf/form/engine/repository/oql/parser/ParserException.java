package net.cf.form.engine.repository.oql.parser;

/**
 * 解析异常
 *
 * @author clouds
 */
@Deprecated
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
