parser grammar FxParser;

options {
    tokenVocab = FxLexer;
    superClass = FxParserBase;
}

fx
    : singleExpression EOF
    ;

singleExpression
    : '(' singleExpression ')'                                             # ParenthesizedExpression
    | singleExpression ('*' | '/' | '%') singleExpression                  # MultiplicativeExpression
    | singleExpression ('+' | '-') singleExpression                        # AdditiveExpression
    | identifier                                                           # IdentifierExpression
    | literal                                                              # LiteralExpression
    ;

literal
    : StringLiteral
    | numericLiteral
    ;

numericLiteral
    : DecimalLiteral
    ;

identifier
    : Identifier
    ;