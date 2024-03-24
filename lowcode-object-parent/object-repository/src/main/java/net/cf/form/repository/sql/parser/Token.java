package net.cf.form.repository.sql.parser;


/**
 * 符号表
 *
 * @author clouds
 */
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

    /* 变量引用、标识符、属性 */
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
    LITERAL_NUMBER,
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
    INNER("INNER"),
    LEFT("LEFT"),
    RIGHT("RIGHT"),
    FULL("FULL"),
    OUTER("OUTER"),
    CROSS("CROSS"),
    JOIN("JOIN"),
    ON("ON"),
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
    COMMENT("COMMENT"),
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
