parser grammar FxParser;

options {
    tokenVocab = FxLexer;
    superClass = FxParserBase;
}

fx
    : singleExpression EOF
    ;

singleExpression
    : singleExpression ('+' | '-') singleExpression                        # AdditiveExpression
    | literal                                                              # LiteralExpression
    ;

literal
    : StringLiteral
    | numericLiteral
    ;

numericLiteral
    : DecimalLiteral
    ;
