parser grammar FxExprParser;

options {
    tokenVocab = FxExprLexer;
    superClass = FxExprParserBase;
}

program
    : expressionStatement
    ;

expressionStatement
    : literal                                                              # LiteralExpression
    ;

literal
    : StringLiteral
    ;