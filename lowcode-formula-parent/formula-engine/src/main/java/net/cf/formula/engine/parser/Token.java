package net.cf.formula.engine.parser;


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

    /* 变量、标识符、子属性 */
    VARIANT,
    IDENTIFIER,
    SEMI(";"),
    COMMA(","),
    DOT("."),
    COLON(":"),


    /* 数值 */
    LATERAL,
    LITERAL_CHARS,
    LITERAL_INT,
    LITERAL_FLOAT,

    /* 位运算符 */
    AMP("&"),
    BAR("|"),
    CARET("^"),
    LTLT("<<"),
    GTGT(">>"),

    /* 比较操作符 */
    GT(">"),
    GTEQ(">="),
    LTGT("<>"),
    BANGEQ("!="),
    EQEQ("=="),
    LT("<"),
    LTEQ("<="),

    /* 逻辑运算符 */
    AMPAMP("&&"),
    BARBAR("||"),
    BANG("!"),
    AND("and"),
    OR("or"),
    NOT("not"),

    /* 一元操作符 */
    IN("in"),
    LIKE("like"),
    CONTAINS("contains"),
    IS("is"),
    NULL("null"),
    EMPTY("empty"),
    ANY("any"),
    ALL("all"),
    TRUE("TRUE"),
    FALSE("FALSE"),

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
}
