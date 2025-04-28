lexer grammar FxLexer;

channels {
    ERROR
}

options {
    superClass = FxLexerBase;
}

StringLiteral:
    ('"' DoubleStringCharacter* '"' | '\'' SingleStringCharacter* '\'') {this.ProcessStringLiteral();}
;

DecimalLiteral:
    DecimalIntegerLiteral '.' [0-9] [0-9_]*
    | '.' [0-9] [0-9_]*
    | DecimalIntegerLiteral
;

// Fragment rules

fragment DoubleStringCharacter: ~["\\\r\n] | '\\' EscapeSequence | LineContinuation;

fragment SingleStringCharacter: ~['\\\r\n] | '\\' EscapeSequence | LineContinuation;

fragment DecimalIntegerLiteral: '0' | [1-9] [0-9_]*;

fragment EscapeSequence:
    CharacterEscapeSequence
;

fragment CharacterEscapeSequence: SingleEscapeCharacter | NonEscapeCharacter;

fragment SingleEscapeCharacter: ['"\\bfnrtv];

fragment NonEscapeCharacter: ~['"\\bfnrtv0-9xu\r\n];

fragment LineContinuation: '\\' [\r\n\u2028\u2029]+;

