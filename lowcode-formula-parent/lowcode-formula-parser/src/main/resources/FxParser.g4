parser grammar ExprParser;

options {
    tokenVocab = JavaScriptLexer;
    // superClass = JavaScriptParserBase;
}

exprProgram
    : expressionStatement
    ;

expressionStatement
    : expressionSequence eos
    ;

expressionSequence
    : assignSingleExpression (',' assignSingleExpression)*
    ;

assignSingleExpression
    :  singleExpression
    ;
//
//
singleExpression
    : singleExpression '?.' singleExpression            # OptionalChainExpression
    | singleExpression ('+' | '-') singleExpression                        # AdditiveExpression
    | identifier                                                           # IdentifierExpression
    | literal                                                              # LiteralExpression
    ;

literal
    : numericLiteral
    ;

numericLiteral
    : DecimalLiteral
    ;
identifier
    : identifierStart kdentifierPart*
    ;


fragment identifierStart: [\p{L}] | [$_] | '\\' UnicodeEscapeSequence;
fragment identifierPart: identifierStart | [\p{Mn}] | [\p{Nd}] | [\p{Pc}] | '\u200C' | '\u200D';

/// Numeric Literals

DecimalLiteral:
    DecimalIntegerLiteral '.' [0-9] [0-9_]* ExponentPart?
    | '.' [0-9] [0-9_]* ExponentPart?
    | DecimalIntegerLiteral ExponentPart?
;

fragment DecimalIntegerLiteral: '0' | [1-9] [0-9_]*;
fragment ExponentPart: [eE] [+-]? [0-9_]+;
fragment UnicodeEscapeSequence:
    'u' HexDigit HexDigit HexDigit HexDigit
    | 'u' '{' HexDigit HexDigit+ '}'
;


eos
    : semiColon
    | EOF
    ;

semiColon
    : ';'
    ;
