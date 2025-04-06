package net.cf.func.ast;

import org.antlr.v4.runtime.*;

/**
 * 自定义错误处理器
 *
 * @author clouds
 */
public class TestErrorHandler extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
        this.underLineError(recognizer, (Token) offendingSymbol, line, charPositionInLine);
    }

    protected void underLineError(
            Recognizer<?, ?> recognizer, Token offendingToken, int line,
            int charPositionInLine) {

        CommonTokenStream tokens = (CommonTokenStream) recognizer.getInputStream();
        String input = tokens.getTokenSource().getInputStream().toString();
        String[] lines = input.split("\n");
        String errorLine = lines[line - 1];
        System.out.println(errorLine);
        for (int i = 0; i < charPositionInLine; i++) {
            System.out.print(" ");
        }

        int start = offendingToken.getStartIndex();
        int stop = offendingToken.getStopIndex();
        if (start >= 0 && stop >= 0) {
            for (int i = start; i <= stop; i++)
                System.out.print("^");
        }
        System.out.println();
    }


}
