parser grammar FxExprParser;

options {
    tokenVocab = FxExprLexer;
    superClass = FxExprParserBase;
}

program
    : expressionStatement EOF
    ;

expressionStatement
    : literal                                                              # LiteralExpression
    ;

literal
    : StringLiteral
    ;