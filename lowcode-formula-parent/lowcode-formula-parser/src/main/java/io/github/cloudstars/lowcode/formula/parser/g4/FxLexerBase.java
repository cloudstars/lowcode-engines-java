package io.github.cloudstars.lowcode.formula.parser.g4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

/**
 * All lexer methods that used in grammar (IsStrictMode)
 * should start with Upper Case Char similar to Lexer rules.
 */
public abstract class FxLexerBase extends Lexer {

    public FxLexerBase() {
    }

    public FxLexerBase(CharStream input) {
        super(input);
    }

    protected void ProcessStringLiteral() {
        /*if (lastToken == null || lastToken.getType() == JavaScriptLexer.OpenBrace)
        {
            String text = getText();
            if (text.equals("\"use strict\"") || text.equals("'use strict'"))
            {
                if (scopeStrictModes.size() > 0)
                    scopeStrictModes.pop();
                useStrictCurrent = true;
                scopeStrictModes.push(useStrictCurrent);
            }
        }*/
    }

}