package net.cf.func.ast;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class UnderlineError {
    protected <T extends Token> void underlineError(
            Recognizer<T, ?> recognizer, Token offendingToken, int line,
            int charPositionInLine) {

        CommonTokenStream tokens = (CommonTokenStream) recognizer
                .getInputStream();

        String input = tokens.getTokenSource().getInputStream().toString();

        String[] lines = input.split("\n");

        String errorLine = lines[line - 1];

        System.out.println(errorLine);

        for (int i = 0; i < charPositionInLine; i++)
            System.out.print(" ");

        int start = offendingToken.getStartIndex();
        int stop = offendingToken.getStopIndex();

        if (start >= 0 && stop >= 0) {
            for (int i = start; i <= stop; i++)
                System.out.print("^");
        }
        System.out.println();
    }
}
