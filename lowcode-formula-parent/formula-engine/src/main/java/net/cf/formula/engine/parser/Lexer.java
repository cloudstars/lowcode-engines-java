package net.cf.formula.engine.parser;

import net.cf.formula.engine.util.StringUtils;

import java.util.Arrays;
import java.util.List;

import static net.cf.formula.engine.parser.LayoutCharacters.EOI;
import static net.cf.formula.engine.parser.Token.EOF;


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
    protected String strVal;

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
     *
     */
    protected int posLine;

    /**
     *
     */
    protected int posColumn;

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

    public Lexer(String text) {
        this.text = text;
        this.pos = -1;
        this.keywords = Keywords.DEFAULT_KEYWORDS;

        scanChar();
    }

    /**
     * 获取下一个 token
     */
    public final void nextToken() {
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
                    this.scanChar();
                    this.token = Token.LPAREN;
                    return;
                case ')':
                case '）':
                    this.scanChar();
                    this.token = Token.RPAREN;
                    return;
                case ',':
                case '，':
                    this.scanChar();
                    this.token = Token.COMMA;
                    return;
                case '*':
                    this.scanChar();
                    this.token = Token.STAR;
                    return;
                case '%':
                    this.scanChar();
                    this.token = Token.PERCENT;
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
                    this.scanChar();
                    this.token = Token.PLUS;
                    return;
                case '-':
                    char next = this.charAt(this.pos + 1);
                    if (next >= '0' && next <= '9') {
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
                    if (this.charAt(this.pos + 1) == 'x') {
                        this.scanChar();
                        this.scanChar();
                        // 扫描十六进制 this.scanHexaDecimal();
                        break;
                    }
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
                case '{':
                    this.scanChar();
                    this.token = Token.LBRACE;
                    return;
                case '}':
                    this.scanChar();
                    this.token = Token.RBRACE;
                    return;
                case ':':
                    this.scanChar();
                    this.token = Token.COLON;
                    return;
                case '[':
                    this.scanChar();
                    this.token = Token.LBRACKET;
                    return;
                case ']':
                    this.scanChar();
                    this.token = Token.RBRACKET;
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
                        token = EOF;
                    } else {
                        lexError("illegal.char", String.valueOf((int) ch));
                        scanChar();
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
            throw new ParserException("illegal identifier. " + this.info());
        } else {
            // 从第二个字符开始扫描
            this.mark = this.pos;
            while (true) {
                this.pos++;
                this.bufPos++;
                this.ch = this.charAt(this.pos);
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
     * 扫描一个字符串
     */
    protected void scanString() {
        this.mark = this.pos;

        // 如果字符串中出现转义符号 "\", 将作特殊处理
        boolean hasSpecial = false;
        if (this.ch != '\'' && this.ch != '\"') {
            lexError("illegal string start char, expected \"\'\" or \"\"\"", String.valueOf((int) ch));
        }

        char startQuot = this.ch;
        boolean prevCharIsEscape;
        while (!this.isEOF()) {
            this.scanChar();
            prevCharIsEscape = this.charAt(this.pos - 1) == '\\';
            if (this.ch == '\\') {
                if (!hasSpecial) {
                    hasSpecial = true;
                    this.initBuff(this.bufPos);
                    this.arraycopy(this.mark + 1, this.buf, 0, this.bufPos);
                }
            } else if (this.ch == startQuot && !prevCharIsEscape) {
                this.token = Token.LITERAL_CHARS;
                if (!hasSpecial) {
                    this.strVal = this.subStr(this.mark + 1, this.bufPos);
                } else {
                    this.strVal = new String(this.buf, 0, this.bufPos);
                }

                this.scanChar();
                return;
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

            /*
            if (this.ch == startQuot) {
                this.scanChar();
                if (this.ch != startQuot) {
                    this.token = Token.LITERAL_CHARS;
                    if (!hasSpecial) {
                        this.strVal = this.subStr(this.mark + 1, this.bufPos);
                    } else {
                        this.strVal = new String(this.buf, 0, this.bufPos);
                    }

                    return;
                }

                // 出现两个
                if (!hasSpecial) {
                    this.initBuff(this.bufPos);
                    this.arraycopy(this.mark + 1, this.buf, 0, this.bufPos);
                    hasSpecial = true;
                }

                this.putToBuf('\'');
            }  else if (!hasSpecial) {
                this.bufPos++;
            } else if (this.bufPos == this.buf.length) {
                this.putToBuf(this.ch);
            } else {
                this.buf[this.bufPos++] = this.ch;
            }*/
        }

        this.lexError("unclosed.str.lit");
    }

    /**
     * 扫描一个数字（整数或浮点数）
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
            this.token = Token.LITERAL_INT;
        } else {
            this.token = Token.LITERAL_FLOAT;
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
                    this.scanChar();
                    this.token = Token.BANGEQ;
                } else {
                    this.token = Token.BANG;
                }
                break;
            case '%':
                this.scanChar();
                this.token = Token.PERCENT;
                break;
            case '&':
                this.scanChar();
                if (this.ch == '&') {
                    this.scanChar();
                    this.token = Token.AMPAMP;
                } else {
                    this.token = Token.AMP;
                }
                break;
            case '*':
                this.scanChar();
                this.token = Token.STAR;
                break;
            case '+':
                this.scanChar();
                this.token = Token.PLUS;
                break;
            case '-':
                this.scanChar();
                this.token = Token.SUB;
                break;
            case '/':
                this.scanChar();
                this.token = Token.SLASH;
                break;
            case '<':
                this.scanChar();
                if (this.ch == '=') {
                    this.scanChar();
                    this.token = Token.LTEQ;
                } else if (this.ch == '>') {
                    this.scanChar();
                    this.token = Token.LTGT;
                } else if (this.ch == '<') {
                    this.scanChar();
                    this.token = Token.LTLT;
                } else {
                    this.token = Token.LT;
                }
                break;
            case '=':
                this.scanChar();
                if (this.ch == '=') {
                    this.scanChar();
                    this.token = Token.EQEQ;
                }
                break;
            case '>':
                this.scanChar();
                if (this.ch == '=') {
                    this.scanChar();
                    this.token = Token.GTEQ;
                } else if (this.ch == '>') {
                    this.scanChar();
                    this.token = Token.GTGT;
                } else {
                    this.token = Token.GT;
                }
                break;
            case '^':
                this.scanChar();
                this.token = Token.CARET;
                break;
            case '|':
                this.scanChar();
                if (this.ch == '|') {
                    this.scanChar();
                    this.token = Token.BARBAR;
                } else {
                    this.token = Token.BAR;
                }
                break;
            default:
                throw new ParserException("TODO. " + this.info());
        }
    }

    public void scanComment() {
        if (this.ch == '/') {
            char nextCh = this.charAt(this.pos + 1);
            if (nextCh == '/') {
                this.scanSingleLineComment();
            } else if (nextCh == '*') {
                this.scanMultiLineComment();
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

        this.strVal = this.subStr(this.mark, this.bufPos);
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
                throw new ParserException("unterminated /* comment. " + this.info());
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

        for (int i = 0; i < this.startPos; ++column) {
            char ch = this.text.charAt(i);
            if (ch == '\n') {
                column = 1;
                ++line;
            }

            ++i;
        }

        this.posLine = line;
        this.posColumn = column;
        StringBuilder buf = new StringBuilder();
        buf.append("pos ").append(this.pos).append(", line ").append(line).append(", column ").append(column);
        if (this.token != null) {
            if (this.token.name != null) {
                buf.append(", token ").append(this.token.name);
            } else {
                buf.append(", token ").append(this.token.name());
            }
        }

        if (this.token == Token.IDENTIFIER || this.token == Token.LITERAL_CHARS) {
            buf.append(" ").append(this.getStringValue());
        }

        if (ParserFeature.isEnabled(this.features, ParserFeature.PrintFxWhileParsingFailed)) {
            buf.append(", fx : ");
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
            return EOI;
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
    public final String getStringValue() {
        if (this.strVal == null) {
            this.strVal = this.subStr(this.mark, this.bufPos);
        }

        return this.strVal;
    }

    public final char[] charsNumberValue() {
        char[] value = this.subChars(this.mark, this.bufPos);
        if (!StringUtils.isNumber(value)) {
            throw new ParserException(value + " is not a number! " + this.info());
        }

        return value;
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
