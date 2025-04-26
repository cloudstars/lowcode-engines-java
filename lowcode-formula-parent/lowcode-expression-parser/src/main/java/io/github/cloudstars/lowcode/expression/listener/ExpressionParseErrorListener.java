package io.github.cloudstars.lowcode.expression.listener;

import io.github.cloudstars.lowcode.expression.exception.ExpressionParseException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2025/4/21
 */
public class ExpressionParseErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String lineInfo = "";
        if (offendingSymbol instanceof CommonToken) {
            CommonToken commonToken = (CommonToken) offendingSymbol;
            lineInfo = "line " + commonToken.getLine() + ":" + commonToken.getCharPositionInLine() + " ";
        }

        throw new ExpressionParseException(lineInfo + msg);
    }
}
