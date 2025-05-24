parser grammar FxParser;

options {
    tokenVocab = FxLexer;
    superClass = FxParserBase;
}

fx
    : expressionSequence EOF
    ;

expressionSequence
    : singleExpression (',' singleExpression)*
    ;

singleExpression
    : '(' singleExpression ')'                                             # ParenthesizedExpression
    | identifier arguments                                                 # FunctionCallExpression
    | '+' singleExpression                                                 # UnaryPlusExpression
    | '-' singleExpression                                                 # UnaryMinusExpression
    | singleExpression ('<' | '>' | '<=' | '>=') singleExpression          # RelationalExpression
    | singleExpression ('*' | '/' | '%') singleExpression                  # MultiplicativeExpression
    | singleExpression ('+' | '-') singleExpression                        # AdditiveExpression
    | identifier                                                           # IdentifierExpression
    | literal                                                              # LiteralExpression
    ;

/* 参数规则 */
arguments
    : '(' (argument (',' argument)* ','?)? ')'
    ;

argument
    : (singleExpression | identifier)
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