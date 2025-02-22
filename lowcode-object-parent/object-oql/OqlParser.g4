parser grammar OqlParser;

options {
    tokenVocab = OqlLexer;
}

// Top Level Description

root
    : sqlStatement EOF
    ;

sqlStatement
    : selectStatement
    ;

selectStatement
    : querySpecification # simpleSelect;

querySpecification
    : SELECT selectElements fromClause
    ;

selectElements
    : (star = '*' | selectElement) (',' selectElement)*
    ;

selectElement
    : fullId '.' '*'                               # selectStarElement
    ;

fromClause
    : (FROM tableSources)
    ;

tableSources
    : tableSource (',' tableSource)*
    ;

tableSource
    : tableSourceItem         # tableSourceBase
    ;

tableSourceItem
    : tableName (AS? alias = uid) # atomTableItem
    ;

tableName
    : fullId
    ;


// Common Clauses

//    DB Objects

fullId
    : uid (DOT_ID | '.' uid)?
    ;

uid
    : simpleId
    | STRING_LITERAL
    ;

simpleId
    : ID
    ;

