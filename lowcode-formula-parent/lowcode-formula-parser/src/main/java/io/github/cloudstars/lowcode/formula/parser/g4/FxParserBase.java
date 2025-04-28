package io.github.cloudstars.lowcode.formula.parser.g4;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

/**
 * All parser methods that used in grammar (p, prev, notLineTerminator, etc.)
 * should start with lower case char similar to parser rules.
 */
public abstract class FxParserBase extends Parser {

    public FxParserBase(TokenStream input) {
        super(input);
    }

}

