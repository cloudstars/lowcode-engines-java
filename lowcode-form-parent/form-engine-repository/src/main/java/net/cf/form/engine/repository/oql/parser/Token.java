package net.cf.form.engine.repository.oql.parser;


/**
 * 符号表
 *
 * @author clouds
 */
@Deprecated
public enum Token {
    /* 括号 */
    LPAREN("("),
    RPAREN(")"),
    LBRACKET("["),
    RBRACKET("]"),
    LBRACE("{"),
    RBRACE("}"),

    /* 操作符号 */
    PLUS("+"),
    SUB("-"),
    STAR("*"),
    SLASH("/"),
    PERCENT("%"),

    /* 变量引用、标识符、子属性 */
    VARIANT_REF,
    IDENTIFIER,
    SEMI(";"),
    COMMA(","),
    DOT("."),
    COLON(":"),


    /* 字面量 */
    LATERAL,
    LITERAL_STRING,
    LITERAL_INT,
    LITERAL_FLOAT,
    NULL("NULL"),
    TRUE("TRUE"),
    FALSE("FALSE"),

    /* 比较操作符 */
    GT(">"),
    GTEQ(">="),
    LTGT("<>"),
    BANGEQ("!="),
    EQ("="),
    EQEQ("=="),
    LT("<"),
    LTEQ("<="),

    /* 逻辑运算符 */
    AMPAMP("&&"),
    BARBAR("||"),
    BANG("!"),
    AND("AND"),
    OR("OR"),
    NOT("NOT"),


    SELECT("SELECT"),
    FROM("FROM"),
    WHERE("WHERE"),
    GROUP("GROUP"),
    HAVING("HAVING"),
    ORDER ("ORDER"),
    BY("BY"),
    ASC("ASC"),
    DESC("DESC"),
    LIMIT("LIMIT"),
    DISTINCT("DISTINCT"),
    AS("AS"),
    IS("IS"),
    IN("IN"),
    LIKE("LIKE"),
    ESCAPE("ESCAPE"),
    CONTAINS("CONTAINS"),
    ANY("ANY"),
    ALL("ALL"),
    INSERT("INSERT"),
    INTO("INTO"),
    VALUES("VALUES"),
    UPDATE("UPDATE"),
    SET("SET"),
    DELETE("DELETE"),

    /* 注释 */
    LINE_COMMENT,
    MULTI_LINE_COMMENT,

    /* 特殊符号 */
    EOF,
    ERROR;

    public final String name;

    private Token() {
        this(null);
    }

    private Token(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
