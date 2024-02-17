package net.cf.form.engine.oql.parser;

/**
 * OQL 解析器
 *
 * @author clouds
 */
public abstract class OqlParser {

    /**
     * 词法分析器
     */
    protected final Lexer lexer;

    /**
     * 出错的位置
     */
    private int errorEndPos;

    public OqlParser(Lexer lexer) {
        this.lexer = lexer;
    }

    public OqlParser(String oql) {
        this(new Lexer(oql));
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
        throw new ParseException(buf.toString());
    }

}
