lexer grammar OqlLexer;

options {
    caseInsensitive = true;
}

channels {
    OQLCOMMENT,
    ERRORCHANNEL
}

// SKIP

SPACE              : [ \t\r\n]+     -> channel(HIDDEN);
COMMENT_INPUT      : '/*' .*? '*/'  -> channel(HIDDEN);
LINE_COMMENT:
    (('--' [ \t]* | '#') ~[\r\n]* ('\r'? '\n' | EOF) | '--' ('\r'? '\n' | EOF)) -> channel(HIDDEN)
;

// Keywords
// Common Keywords
AS                            : 'AS';
FROM                          : 'FROM';
SELECT                        : 'SELECT';
WHERE                         : 'WHERE';

// Operators. Arithmetics

STAR   : '*';

// Constructors symbols

DOT                : '.';
LR_BRACKET         : '(';
RR_BRACKET         : ')';
COMMA              : ',';

// Literal Primitives

STRING_LITERAL                : DQUOTA_STRING | SQUOTA_STRING | BQUOTA_STRING;

DOT_ID: '.' ID_LITERAL;


// Identifiers

ID: ID_LITERAL;


// Fragments

fragment ID_LITERAL        : [A-Z_$0-9\u0080-\uFFFF]*? [A-Z_$\u0080-\uFFFF]+? [A-Z_$0-9\u0080-\uFFFF]*;
fragment DQUOTA_STRING     : '"' ( '\\' . | '""' | ~('"' | '\\'))* '"';
fragment SQUOTA_STRING     : '\'' ('\\' . | '\'\'' | ~('\'' | '\\'))* '\'';
fragment BQUOTA_STRING     : '`' ( ~'`' | '``')* '`';

