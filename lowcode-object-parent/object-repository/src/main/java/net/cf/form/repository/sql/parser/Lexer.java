package net.cf.form.repository.sql.parser;

import java.util.Arrays;
import java.util.List;


/**
 * 词法解析器
 *
 * @author clouds
 */
public class Lexer {

    protected Keywords keywords;

    /**
     * 原始输入的文本
     */
    public final String text;

    /**
     * 解析器的特性
     */
    protected int features;

    /**
     * 最新扫描到的token（一次扫描结束后生成）
     */
    protected Token token;

    /**
     * 最新扫描到的字符串token（当token是字符串时更新）
     */
    private String strVal;

    /**
     * 当前扫描的位置
     */
    protected int pos;

    /**
     * 最新扫描的字符
     */
    protected char ch;

    /**
     * 当前扫描过程中的字符缓存池
     */
    protected char[] buf;

    /**
     * 最新扫描的行数
     */
    protected int line;

    /**
     * 获取下一个token时扫描开始的位置
     */
    private int startPos;

    /**
     * 获取下一个token时扫描缓冲池的位置
     */
    protected int bufPos;

    /**
     * 获取下一个token时（过程中）扫描到的行数
     */
    protected int lines;

    /**
     * 获取下一个token时（过程中）扫描到的注释行
     */
    protected List<String> comments;

    /**
     *
     */
    protected int mark;

    protected boolean skipComment;
    protected boolean keepComments;
    protected boolean optimizedForParameterized;
    protected boolean keepSourceLocation;

    public Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.ch = this.charAt(this.pos);
        this.keywords = Keywords.DEFAULT_KEYWORDS;

        // 初始化后扫描第一个 Token
        this.nextToken();
    }

    public void config(SqlParserFeature feature, boolean state) {
        this.features = SqlParserFeature.config(this.features, feature, state);
        if (feature == SqlParserFeature.OPTIMIZED_FOR_PARAMETERIZED) {
            this.optimizedForParameterized = state;
        } else if (feature == SqlParserFeature.KEEP_COMMENTS) {
            this.keepComments = state;
        } else if (feature == SqlParserFeature.KEEP_SOURCE_LOCATION) {
            this.keepSourceLocation = state;
        } else if (feature == SqlParserFeature.SKIP_COMMENTS) {
            this.skipComment = state;
        }
    }

    /**
     * 获取当前的 Token
     *
     * @return
     */
    public final Token token() {
        return this.token;
    }


    /**
     * 获取下一个 token
     */
    public final void nextToken() {
        if (this.token == Token.EOF) {
            throw new SqlParseException("end of file error.");
        }

        this.startPos = this.pos;
        this.bufPos = 0;
        if (this.comments != null && this.comments.size() > 0) {
            this.comments = null;
        }

        this.lines = 0;
        int startLine = line;

        while (true) {
            /* 判断是否是一个空白符，并忽略空白符 */
            if (CharTypes.isWhitespace(ch)) {
                if (ch == '\n') {
                    line++;
                    lines = line - startLine;
                }

                ch = charAt(++pos);
                continue;
            }

            if (CharTypes.isFirstIdentifierChar(this.ch)) {
                this.scanIdentifier();
                return;
            }

            switch (ch) {
                case '(':
                case '（':
                    this.token = Token.LPAREN;
                    this.scanChar();
                    return;
                case ')':
                case '）':
                    this.token = Token.RPAREN;
                    this.scanChar();
                    return;
                case ',':
                case '，':
                    this.token = Token.COMMA;
                    this.scanChar();
                    return;
                case '*':
                    this.token = Token.STAR;
                    this.scanChar();
                    return;
                case '%':
                    this.token = Token.PERCENT;
                    this.scanChar();
                    return;
                case '/':
                    this.scanChar();
                    if (this.ch == '/') {
                        this.scanSingleLineComment();
                    } else if (this.ch == '*') {
                        this.scanMultiLineComment();
                    } else {
                        this.token = Token.SLASH;
                    }

                    if ((this.token == Token.LINE_COMMENT || this.token == Token.MULTI_LINE_COMMENT)) {
                        this.bufPos = 0;
                        break;
                    }

                    return;
                case '+':
                    this.token = Token.PLUS;
                    this.scanChar();
                    return;
                case '-':
                    char next = this.charAt(this.pos + 1);
                    if (next == '-') {
                        this.scanComment();
                        if (this.token == Token.LINE_COMMENT) {
                            this.bufPos = 0;
                            break;
                        }
                    } else if (next >= '0' && next <= '9') {
                        this.scanNumber();
                    } else {
                        this.scanOperator();
                    }

                    return;
                case '\'':
                case '\"':
                    this.scanString();
                    return;
                case '.':
                    this.scanChar();
                    if (!CharTypes.isDigit(this.ch) || this.pos != 1 && this.token == Token.IDENTIFIER) {
                        this.token = Token.DOT;
                        return;
                    }

                    this.unScanChar();
                    this.scanNumber();
                    return;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    this.scanNumber();
                    return;
                case ';':
                    this.token = Token.SEMI;
                    this.scanChar();
                    return;
                case '{':
                    this.token = Token.LBRACE;
                    this.scanChar();
                    return;
                case '}':
                    this.token = Token.RBRACE;
                    this.scanChar();
                    return;
                case ':':
                    this.token = Token.COLON;
                    this.scanChar();
                    return;
                case '[':
                    this.token = Token.LBRACKET;
                    this.scanChar();
                    return;
                case ']':
                    this.token = Token.RBRACKET;
                    this.scanChar();
                    return;
                case '$':
                case '#':
                    this.scanVariableRef();
                    this.token = Token.VARIANT_REF;
                    return;
                default:
                    if (Character.isLetter(ch)) {
                        scanIdentifier();
                        return;
                    }

                    if (isOperator(ch)) {
                        scanOperator();
                        return;
                    }

                    if (isEOF()) {
                        token = Token.EOF;
                    } else {
                        throw new SqlParseException("illegal.char, " + this.ch + ", " + this.info());
                    }

                    return;
            }
        }
    }

    /**
     * 扫描一个字符
     */
    protected final void scanChar() {
        this.scanChar(false);
    }

    /**
     * 扫描一个字符，并且同步buf指针
     *
     * @param syncBuf
     */
    protected final void scanChar(boolean syncBuf) {
        this.ch = this.charAt(++this.pos);
        if (syncBuf) {
            ++this.bufPos;
        }
    }

    /**
     * 回退一个字符
     */
    protected void unScanChar() {
        this.ch = this.charAt(--this.pos);
    }


    /**
     * 扫描一个标识符
     */
    public void scanIdentifier() {
        char first = this.ch;
        boolean firstFlag = CharTypes.isFirstIdentifierChar(first);
        if (!firstFlag) {
            throw new SqlParseException("illegal identifier. " + this.info());
        } else {
            this.mark = this.pos;
            // 从第二个字符开始扫描
            while (true) {
                this.scanChar(true);
                if (!CharTypes.isIdentifierChar(ch)) {
                    String strVal = this.subStr(this.mark, this.bufPos);
                    Token tk = this.keywords.getKeyword(strVal);
                    if (tk != null) {
                        this.token = tk;
                        this.strVal = null;
                    } else {
                        this.token = Token.IDENTIFIER;
                        this.strVal = strVal;
                    }

                    return;
                }
            }
        }
    }

    /**
     * 扫描一个变量引用：${xxx} 或 #{xxx} 或 #{xxx(a, b, c)}
     */
    private void scanVariableRef() {
        this.mark = this.pos;
        this.scanChar(true);
        if (this.ch == '{') {
            do {
                this.scanChar(true);
                if (this.ch == '(') { // #{var(subVar1, subVar2, ...)}
                    do {
                        this.scanChar(true);
                    } while (CharTypes.isIdentifierChar(this.ch) || this.ch == ',' || this.ch == ' ' || this.ch == '\r' || this.ch == '\n');

                    if (this.ch != ')') {
                        throw new SqlParseException("illegal variable with sub variable, unterminated with ')'. " + this.info());
                    }

                    this.scanChar(true);
                }
            } while (CharTypes.isIdentifierChar(this.ch) || this.ch == '.'); // 变量名允许带点
        }

        if (this.ch != '}') {
            throw new SqlParseException("illegal variable, unterminated with '}'. " + this.info());
        } else {
            // 扫描过变量引用结束符："}"
            this.scanChar(true);
            // 变量长度至少是4，如：${v}
            if (this.bufPos < 4) {
                throw new SqlParseException("illegal variable. " + this.info());
            }
            this.strVal = this.subStr(this.mark, this.bufPos);
            // 删除空格回车换行等无效字符
            this.strVal = strVal.replaceAll("\\r\\n|\\r|\\n", "").replaceAll(" ", "");
        }
    }

    /**
     * 扫描一个字符串，如：'abc' 或 "abc"，两边的引号必须一致，字符串中需要引号时，写两次，如：'abc''abc' 或 ”abc""abc“
     */
    protected void scanString() {
        this.mark = this.pos;

        char startChar = this.ch;
        // 如果字符串中出现转义符号'', 将作特殊处理
        boolean hasSpecial = false;
        while (!this.isEOF()) {
            this.scanChar();
            if (this.ch == startChar) {
                if (this.charAt(this.pos + 1) == startChar) {
                    this.scanChar();
                    if (!hasSpecial) {
                        hasSpecial = true;
                        this.initBuff(this.bufPos);
                        this.arraycopy(this.mark + 1, this.buf, 0, this.bufPos);
                    }
                    this.putToBuf(startChar);
                } else {
                    this.token = Token.LITERAL_STRING;
                    if (!hasSpecial) {
                        this.strVal = this.subStr(this.mark + 1, this.bufPos);
                    } else {
                        this.strVal = new String(this.buf, 0, this.bufPos);
                    }

                    this.scanChar();
                    return;
                }
            } else {
                if (!hasSpecial) {
                    this.bufPos++;
                } else {
                    if (this.bufPos == this.buf.length) {
                        this.putToBuf(this.ch);
                    } else {
                        this.buf[this.bufPos++] = this.ch;
                    }
                }
            }
        }

        throw new SqlParseException("unclosed str. " + this.info());
    }

    /**
     * 扫描一个数字（整数或浮点数）,也可能是一个标识符
     */
    public void scanNumber() {
        this.mark = this.pos;
        int numberSale = 0;
        boolean numberExp = false;

        if (this.ch == '-') {
            this.scanChar(true);
        }

        while (this.ch >= '0' && this.ch <= '9') {
            this.scanChar(true);
        }

        if (this.ch == '.') {
            this.scanChar(true);

            for (numberSale = 0; this.ch >= '0' && this.ch <= '9'; numberSale++) {
                this.scanChar(true);
            }

            numberExp = true;
        }

        if (numberSale <= 0 && !numberExp) {
            // 判断下是否以数字开头的变量，如：123abc
            if (!CharTypes.isFirstIdentifierChar(this.ch)) {
                this.token = Token.LITERAL_INT;
            } else {
                while (true) {
                    this.scanChar(true);
                    if (!CharTypes.isIdentifierChar(this.ch)) {
                        break;
                    }
                }

                this.token = Token.IDENTIFIER;
            }
        } else {
            this.token = Token.LITERAL_NUMBER;
        }

        this.strVal = this.subStr(this.mark, this.bufPos);
    }

    /**
     * 扫描操作符
     */
    private void scanOperator() {
        switch (this.ch) {
            case '!':
                this.scanChar();
                while (CharTypes.isWhitespace(this.ch)) {
                    this.scanChar();
                }

                if (this.ch == '=') {
                    this.token = Token.BANGEQ;
                    this.scanChar();
                } else {
                    this.token = Token.BANG;
                }
                break;
            case '%':
                this.token = Token.PERCENT;
                this.scanChar();
                break;
            case '&':
                this.scanChar();
                if (this.ch == '&') {
                    this.token = Token.AMPAMP;
                    this.scanChar();
                }
                break;
            case '*':
                this.token = Token.STAR;
                this.scanChar();
                break;
            case '+':
                this.token = Token.PLUS;
                this.scanChar();
                break;
            case '-':
                this.token = Token.SUB;
                this.scanChar();
                break;
            case '/':
                this.token = Token.SLASH;
                this.scanChar();
                break;
            case '<':
                this.scanChar();
                if (this.ch == '=') {
                    this.token = Token.LTEQ;
                    this.scanChar();
                } else if (this.ch == '>') {
                    this.token = Token.LTGT;
                    this.scanChar();
                } else {
                    this.token = Token.LT;
                }
                break;
            case '=':
                this.scanChar();
                if (this.ch == '=') {
                    this.token = Token.EQEQ;
                    this.scanChar();
                } else {
                    this.token = Token.EQ;
                }
                break;
            case '>':
                this.scanChar();
                if (this.ch == '=') {
                    this.token = Token.GTEQ;
                    this.scanChar();
                } else {
                    this.token = Token.GT;
                }
                break;
            case '|':
                this.scanChar();
                if (this.ch == '|') {
                    this.token = Token.BARBAR;
                    this.scanChar();
                }
                break;
            default:
                throw new SqlParseException("TODO. " + this.info());
        }
    }

    /**
     * 扫描注释 多行注释：/* ... * /，单行注释：//，单行注释：--
     */
    private void scanComment() {
        char nextCh = this.charAt(this.pos + 1);
        if (this.ch == '/') {
            if (nextCh == '/') {
                this.scanSingleLineComment();
            } else if (nextCh == '*') {
                this.scanMultiLineComment();
            } else {
                throw new IllegalStateException();
            }
        } else if (this.ch == '-') {
            if (nextCh == '-') {
                this.scanSingleLineComment();
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private void scanSingleLineComment() {
        this.scanChar();
        this.scanChar();
        this.mark = this.pos;
        this.bufPos = 2;

        while (true) {
            if (this.ch == '\r') {
                if (this.charAt(this.pos + 1) == '\n') {
                    this.line++;
                    this.scanChar();
                } else {
                    this.bufPos++;
                }
                break;
            }

            if (this.ch == '\n') {
                this.line++;
                this.scanChar();
                break;
            }

            if (this.ch == CharTypes.EOF) {
                break;
            }

            this.scanChar();
            this.bufPos++;
        }

        this.strVal = this.subStr(this.mark, this.bufPos - 2);
        this.token = Token.LINE_COMMENT;
    }

    private void scanMultiLineComment() {
        int depth = 1;
        this.scanChar();
        this.scanChar();
        this.mark = this.pos;
        this.bufPos = 0;

        while (true) {
            if (this.ch == '/' && this.charAt(this.pos + 1) == '*') {
                this.scanChar();
                this.scanChar();
                ++depth;
            }

            if (this.ch == '*' && this.charAt(this.pos + 1) == '/') {
                this.scanChar();
                this.scanChar();
                --depth;
                if (0 == depth) {
                    this.strVal = this.subStr(this.mark, this.bufPos);
                    this.token = Token.MULTI_LINE_COMMENT;

                    return;
                }
            }

            if (this.ch == CharTypes.EOF) {
                throw new SqlParseException("unterminated /* comment. " + this.info());
            }

            this.scanChar();
            this.bufPos++;
        }
    }


    protected void initBuff(int size) {
        if (this.buf == null) {
            if (size < 32) {
                this.buf = new char[32];
            } else {
                this.buf = new char[size + 32];
            }
        } else if (this.buf.length < size) {
            this.buf = Arrays.copyOf(this.buf, size);
        }
    }

    public void arraycopy(int srcPos, char[] dest, int destPos, int length) {
        this.text.getChars(srcPos, srcPos + length, dest, destPos);
    }

    protected final void putToBuf(char ch) {
        if (this.bufPos == this.buf.length) {
            char[] newBuf = new char[this.buf.length * 2];
            System.arraycopy(this.buf, 0, newBuf, 0, this.buf.length);
            this.buf = newBuf;
        }

        this.buf[this.bufPos++] = ch;
    }


    /**
     * 获取当前的信息
     *
     * @return
     */
    public String info() {
        int line = 1;
        int column = 1;

        for (int i = 0; i < this.startPos; i++) {
            char ch = this.text.charAt(i);
            if (ch == '\n') {
                line++;
                column = 1;
            } else {
                column++;
            }
        }

        StringBuilder buf = new StringBuilder();
        buf.append("pos ").append(this.pos).append(", line ").append(line).append(", column ").append(column);
        if (this.token != null) {
            if (this.token.name != null) {
                buf.append(", token ").append(this.token.name);
            } else {
                buf.append(", token ").append(this.token.name());
            }
        }

        if (this.token == Token.IDENTIFIER || this.token == Token.LITERAL_STRING) {
            buf.append(" ").append(this.strVal);
        }

        if (ParserFeature.isEnabled(this.features, ParserFeature.PRINT_SQL_WHILE_PARSING_FAILED)) {
            buf.append(", method : ");
            buf.append(this.text);
        }

        return buf.toString();
    }

    /**
     * 获取第index个字符
     *
     * @param index
     * @return
     */
    public final char charAt(int index) {
        if (index >= text.length()) {
            return LayoutCharacters.EOI;
        }

        return text.charAt(index);
    }

    /**
     * 字符串截取
     *
     * @param offset 起始位置
     * @param count  数量
     * @return
     */
    public final String subStr(int offset, int count) {
        return this.text.substring(offset, offset + count);
    }

    public final char[] subChars(int offset, int count) {
        char[] chars = new char[count];
        this.text.getChars(offset, offset + count, chars, 0);
        return chars;
    }

    /**
     * 获取当前扫描到的字符串
     *
     * @return
     */
    public final String stringVal() {
        if (this.strVal == null) {
            this.strVal = this.subStr(this.mark, this.bufPos);
        }

        return this.strVal;
    }

    /**
     * 是否已经到达了结束的位置
     *
     * @return
     */
    public boolean isEOF() {
        return this.pos >= this.text.length();
    }

    /**
     * 是否操作符号
     *
     * @param ch
     * @return
     */
    private boolean isOperator(char ch) {
        switch (ch) {
            case '!':
            case '%':
            case '&':
            case '*':
            case '+':
            case '-':
            case ';':
            case '<':
            case '=':
            case '>':
            case '^':
            case '|':
            case '~':
                return true;
            default:
                return false;
        }
    }

    private SavePoint savePoint;

    public static class SavePoint {
        int bp;
        int sp;
        int np;
        char ch;
        public Token token;
        String stringVal;

        public SavePoint() {
        }
    }

    public SavePoint mark() {
        SavePoint savePoint = new SavePoint();
        savePoint.bp = this.pos;
        savePoint.sp = this.bufPos;
        savePoint.np = this.mark;
        savePoint.ch = this.ch;
        savePoint.token = this.token;
        savePoint.stringVal = this.strVal;
        return this.savePoint = savePoint;
    }

    public void reset(SavePoint savePoint) {
        this.pos = savePoint.bp;
        this.bufPos = savePoint.sp;
        this.mark = savePoint.np;
        this.ch = savePoint.ch;
        this.token = savePoint.token;
        this.strVal = savePoint.stringVal;
    }


    /**
     * 词法错误
     *
     * @param key
     * @param args
     */
    protected void lexError(String key, Object... args) {
        this.token = Token.ERROR;
    }

}
