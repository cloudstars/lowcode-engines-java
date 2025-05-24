lexer grammar FxLexer;

channels {
    ERROR
}

options {
    superClass = FxLexerBase;
}

OpenParen                  : '(';
CloseParen                 : ')';
Comma                      : ',';
Assign                     : '=';
Plus                       : '+';
Minus                      : '-';
Multiply                   : '*';
Divide                     : '/';
Modulus                    : '%';
LessThan                   : '<';
MoreThan                   : '>';
LessThanEquals             : '<=';
GreaterThanEquals          : '>=';
WS  :   [ \t\r\n]+ -> skip ; // 表示忽略空格

StringLiteral:
    ('"' DoubleStringCharacter* '"' | '\'' SingleStringCharacter* '\'') {this.ProcessStringLiteral();}
;

DecimalLiteral:
    DecimalIntegerLiteral '.' [0-9] [0-9_]*
    | '.' [0-9] [0-9_]*
    | DecimalIntegerLiteral
;

Identifier: IdentifierStart IdentifierPart*;

MultiLineComment  : '/*' .*? '*/'             -> channel(HIDDEN);
SingleLineComment : '//' ~[\r\n\u2028\u2029]* -> channel(HIDDEN);


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

fragment IdentifierPart: IdentifierStart | [\p{Mn}] | [\p{Nd}] | [\p{Pc}] | '\u200C' | '\u200D';

fragment IdentifierStart: [\p{L}] | [$_];

fragment UnicodeEscapeSequence:
    'u' HexDigit HexDigit HexDigit HexDigit
    | 'u' '{' HexDigit HexDigit+ '}'
;

fragment HexDigit: [_0-9a-fA-F];

