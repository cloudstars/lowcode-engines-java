package io.github.cloudstars.lowcode.formula.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * 公式解析错误监听器
 *
 * @author clouds
 */
public class FormulaParseErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        StringBuilder errors = new StringBuilder();
        if (offendingSymbol instanceof CommonToken) {
            CommonToken token = (CommonToken) offendingSymbol;
            errors.append("line ").append(token.getLine()).append(":").append(token.getCharPositionInLine());
        }

        errors.append(" ").append(msg);
        throw new FormulaParseException(errors.toString());
    }
}
