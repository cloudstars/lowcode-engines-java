parser grammar FxParser;

options {
    tokenVocab = FxLexer;
    superClass = FxParserBase;
}

fx
    : fxStatement EOF
    ;

fxStatement
    : literal                                                              # LiteralExpression
    ;

literal
    : StringLiteral
    | numericLiteral
    ;

numericLiteral
    : DecimalLiteral
    ;
