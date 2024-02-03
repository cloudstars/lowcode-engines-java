
package net.cf.form.repository.sql.parser;

/**
 * SQL 解析器
 *
 * @author clouds
 */
public abstract class SqlParser {

    /**
     * 词法分析器
     */
    protected final Lexer lexer;

    /**
     * 出错的位置
     */
    private int errorEndPos;

    public SqlParser(Lexer lexer) {
        this.lexer = lexer;
    }

    public SqlParser(String Sql) {
        this(new Lexer(Sql));
    }

    public SqlParser(String sql, SqlParserFeature... features) {
        this(sql);

        for(int i = 0, l = features.length; i < l; i++) {
            SqlParserFeature feature = features[i];
            this.config(feature, true);
        }

        this.lexer.nextToken();
    }

    public void config(SqlParserFeature feature, boolean state) {
        this.lexer.config(feature, state);
    }

    public final Lexer getLexer() {
        return this.lexer;
    }



    /**
     * 断言接收一个指定的 Token
     *
     * @param token
     */
    protected void accept(Token token) {
        if (this.lexer.token == token) {
            this.lexer.nextToken();
        } else {
            this.setErrorEndPos(this.lexer.pos);
            this.printError(token);
        }
    }


    /**
     * 设置错误位置
     *
     * @param errPos
     */
    protected void setErrorEndPos(int errPos) {
        if (errPos > this.errorEndPos) {
            this.errorEndPos = errPos;
        }
    }


    /**
     * 打印出错信息
     *
     * @param token
     */
    protected void printError(Token token) {
        String around;
        if (this.lexer.mark >= 0 && this.lexer.text.length() > this.lexer.mark + 30) {
            int begin;
            int end;
            if (this.lexer.mark - 5 > 0) {
                begin = this.lexer.mark - 5;
                end = this.lexer.mark + 30;
            } else {
                begin = this.lexer.mark;
                end = this.lexer.mark + 30;
            }

            if (begin < 10) {
                begin = 0;
            } else {
                for (int i = 1; i < 10 && i < begin; ++i) {
                    char ch = this.lexer.text.charAt(begin - i);
                    if (ch == ' ' || ch == '\n') {
                        begin = begin - i + 1;
                    }
                }
            }

            around = this.lexer.text.substring(begin, end);
        } else if (this.lexer.mark >= 0) {
            if (this.lexer.mark - 5 > 0) {
                around = this.lexer.text.substring(this.lexer.mark - 5);
            } else {
                around = this.lexer.text.substring(this.lexer.mark);
            }
        } else {
            around = this.lexer.text;
        }

        StringBuilder buf = (new StringBuilder()).append("syntax error, error in :'").append(around).append("'");
        if (token != this.lexer.token) {
            buf.append(", expect ").append(token.name).append(", actual ").append(this.lexer.token.name);
        }

        buf.append(", ").append(this.lexer.info());
        throw new ParserException(buf.toString());
    }

}
