parser grammar ExprParser;

options {
    tokenVocab = JavaScriptLexer;
    superClass = JavaScriptParserBase;
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


singleExpression
    : singleExpression '?.' singleExpression            # OptionalChainExpression
    | singleExpression '?.'? '['  expressionSequence ']' # MemberIndexExpression
    | Dot singleExpression     # MemberDotExpression2
    | singleExpression '?'?  Dot '#'? identifierName     # MemberDotExpression
    | singleExpression arguments                                           # ArgumentsExpression
    | singleExpression {this.notLineTerminator()}? '++'                    # PostIncrementExpression
    | singleExpression {this.notLineTerminator()}? '--'                    # PostDecreaseExpression
    | Typeof singleExpression                                              # TypeofExpression
    | '++' singleExpression                                                # PreIncrementExpression
    | '--' singleExpression                                                # PreDecreaseExpression
    | '+' singleExpression                                                 # UnaryPlusExpression
    | '-' singleExpression                                                 # UnaryMinusExpression
    | '~' singleExpression                                                 # BitNotExpression
    | '!' singleExpression                                                 # NotExpression
    | <assoc = right> singleExpression '**' singleExpression               # PowerExpression
    | singleExpression ('*' | '/' | '%') singleExpression                  # MultiplicativeExpression
    | singleExpression ('+' | '-') singleExpression                        # AdditiveExpression
    | singleExpression ('<' | '>' | '<=' | '>=') singleExpression          # RelationalExpression
    | singleExpression ('==' | '!=' | '===' | '!==') singleExpression      # EqualityExpression
    | singleExpression '&' singleExpression                                # BitAndExpression
    | singleExpression '^' singleExpression                                # BitXOrExpression
    | singleExpression '|' singleExpression                                # BitOrExpression
    | singleExpression '&&' singleExpression                               # LogicalAndExpression
    | singleExpression '||' singleExpression                               # LogicalOrExpression
    | singleExpression '?' singleExpression ':' singleExpression           # TernaryExpression
        | <assoc = right> singleExpression  '='  singleExpression                # AssignmentExpression
    | <assoc = right> singleExpression assignmentOperator singleExpression # AssignmentOperatorExpression
    | singleExpression templateStringLiteral                               # TemplateStringExpression // ECMAScript 6
    | identifier                                                           # IdentifierExpression
    | literal                                                              # LiteralExpression
    | arrayLiteral                                     # ArrayLiteralExpression
    | objectLiteral                                                        # ObjectLiteralExpression
    | leftParenthesis expressionSequence ')'                                           # ParenthesizedExpression
    ;

arguments
    : leftParenthesis (argument (',' argument)* ','?)? ')'
    ;

argument
    : Ellipsis? (assignSingleExpression | identifier)
    ;

templateStringLiteral
    : BackTick templateStringAtom* BackTick
    ;

templateStringAtom
    : TemplateStringAtom
    | TemplateStringStartExpression assignSingleExpression TemplateCloseBrace
    ;

literal
    : NullLiteral
    | BooleanLiteral
    | StringLiteral
    | templateStringLiteral
    | numericLiteral
    | bigintLiteral
    ;

numericLiteral
    : DecimalLiteral
    | HexIntegerLiteral
    | OctalIntegerLiteral
    | OctalIntegerLiteral2
    | BinaryIntegerLiteral
    ;

bigintLiteral
    : BigDecimalIntegerLiteral
    | BigHexIntegerLiteral
    | BigOctalIntegerLiteral
    | BigBinaryIntegerLiteral
    ;

arrayLiteral
    : ( '['  elementList  ']')
    ;

// JavaScript supports arrasys like [,,1,2,,].
elementList
    : ','* arrayElement? (','+ arrayElement) * ','* // Yes, everything is optional
    ;

arrayElement
    : Ellipsis? assignSingleExpression
    ;

objectLiteral
    : leftBrace (propertyAssignment (','  propertyAssignment)* ','?)? rightBrace
    ;

propertyAssignment
    : propertyName ':' assignSingleExpression                                  # PropertyExpressionAssignment
    | '['  assignSingleExpression ']' ':' assignSingleExpression                      # ComputedPropertyExpressionAssignment
    | Ellipsis? assignSingleExpression                                         # PropertyShorthand
    ;

propertyName
    : identifierName
    | StringLiteral
    | numericLiteral
    | '['  assignSingleExpression ']'
    ;

identifierName
    : identifier
    | reservedWord
    ;


identifier
    : Identifier
    | NonStrictLet
    | Async
    | As
    | From
    | Yield
    | Of
    ;

reservedWord
    : keyword
    | NullLiteral
    | BooleanLiteral
    ;

keyword
    : Break
    | Do
    | Instanceof
    | Typeof
    | Case
    | Else
    | New
    | Var
    | Catch
    | Finally
    | Return
    | Void
    | Continue
    | For
    | Switch
    | While
    | Debugger
    | Function_
    | This
    | With
    | Default
    | If
    | Throw
    | Delete
    | In
    | Try
    | Class
    | Enum
    | Extends
    | Super
    | Const
    | Export
    | Import
    | Implements
    | let_
    | Private
    | Public
    | Interface
    | Package
    | Protected
    | Static
    | Yield
    | YieldStar
    | Async
    | Await
    | From
    | As
    | Of
    ;

let_
    : NonStrictLet
    | StrictLet
    ;

assignmentOperator
    : '*='
    | '/='
    | '%='
    | '+='
    | '-='
    | '<<='
    | '>>='
    | '>>>='
    | '&='
    | '^='
    | '|='
    | '**='
    | '??='
    ;

leftParenthesis
    : '('
    ;

leftBrace
    : '{'
    ;

rightBrace
    : '}'
    ;

eos
    : SemiColon
    | EOF
    | LineTerminator*
    | {this.lineTerminatorAhead()}?
    | {this.closeBrace()}?
    ;
