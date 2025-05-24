package io.github.cloudstars.lowcode.formula.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * 公式解析错误监听器
 *
 * @author clouds
 */
public class FormulaParseErrorListener extends BaseErrorListener {

    private StringBuilder errors;

    public FormulaParseErrorListener() {
        this.errors = new StringBuilder();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errors.append("line " + line + ":" + charPositionInLine + " " + msg);
        errors.append(System.lineSeparator());
    }

    /**
     * 获取错误消息
     *
     * @return 错误消息
     */
    public String getErrors() {
        return this.errors.toString();
    }

}
