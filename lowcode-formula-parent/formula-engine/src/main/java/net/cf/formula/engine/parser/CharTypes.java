package net.cf.formula.engine.parser;

/**
 * 字符类型工具类
 *
 * @author clouds
 */
public class CharTypes {

    final static char EOF = 26;

    /* ASCII码表的长度 */
    final static int ASCII_SIZE = 256;

    /* 全角空格 */
    final static int CHINESE_WHITESPACE = 12288;

    /**
     * 空白字符特征数组
     */
    private static final boolean[] whitespaceFlags = new boolean[ASCII_SIZE];

    /* 标识符首字符的特征数组 */
    private static final boolean[] firstIdentifierFlags = new boolean[ASCII_SIZE];

    /* 标识符中允许出现的字符的特征数组 */
    private static final boolean[] identifierFlags = new boolean[ASCII_SIZE];

    /**
     * 十六进制字符特征数组
     */
    private static final boolean[] hexFlags = new boolean[ASCII_SIZE];

    private static final String[] stringCache = new String[ASCII_SIZE];

    static {
        initWhitespaceFlags();
        initFirstIdentifierFlags();
        initIdentifierFlags();
        initHexFlags();
        initStringCache();
    }


    public CharTypes() {
    }


    /**
     * 是否标识符首字符
     *
     * @param c
     * @return
     */
    public static boolean isFirstIdentifierChar(char c) {
        return c <= firstIdentifierFlags.length && firstIdentifierFlags[c];
    }

    /**
     * 判断是否标识符中的字母
     *
     * @param c
     * @return
     */
    public static boolean isIdentifierChar(char c) {
        return c <= identifierFlags.length && identifierFlags[c];
    }

    /**
     * 是否16进制字符
     *
     * @param c
     * @return
     */
    public static boolean isHex(char c) {
        return c < hexFlags.length && hexFlags[c];
    }

    /**
     * 是否数字
     *
     * @param c
     * @return
     */
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static String valueOf(char ch) {
        return ch < stringCache.length ? stringCache[ch] : null;
    }

    public static boolean isWhitespace(char c) {
        return c <= whitespaceFlags.length && whitespaceFlags[c] || c == CHINESE_WHITESPACE;
    }

    /**
     * 初始化十六进制字符特征数组
     */
    private static void initHexFlags() {
        for (int i = 0; i < hexFlags.length; i++) {
            if (i >= 'A' && i <= 'F') {
                hexFlags[i] = true;
            } else if (i >= 'a' && i <= 'f') {
                hexFlags[i] = true;
            } else if (i >= '0' && i <= '9') {
                hexFlags[i] = true;
            }
        }
    }

    /**
     * 初始化空白符特性数组
     */
    private static void initWhitespaceFlags() {
        // 0 ~ 31为控制桌字符或通信专用制字符，32为空格
        for (int i = 0; i <= 32; ++i) {
            whitespaceFlags[i] = true;
        }

        // EOI不算空白字符
        whitespaceFlags[LayoutCharacters.EOI] = false;

        // 大于0x7F的均为空白字符，0x7F为DEL，0xA0为不间断空格
        for (int i = 0x7F; i <= 0xA0; ++i) {
            whitespaceFlags[i] = true;
        }
    }

    /**
     * 初始化标识符首字母特性数组
     */
    private static void initFirstIdentifierFlags() {
        // 26个英母字母的大小写允许作为标识符的首字母
        for (int i = 0; i < firstIdentifierFlags.length; i++) {
            if (i >= 'A' && i <= 'Z') {
                firstIdentifierFlags[i] = true;
            } else if (i >= 'a' && i <= 'z') {
                firstIdentifierFlags[i] = true;
            }
        }

        firstIdentifierFlags['$'] = true;
        firstIdentifierFlags['_'] = true;
    }

    /**
     * 初始化标识符中字母（非首字母）的特性数据
     */
    private static void initIdentifierFlags() {
        for (int i = 0; i < identifierFlags.length; i++) {
            // 26个英母字母的大小写、10个数字允许作为标识符中的字母（非首字母）
            if (i >= 'A' && i <= 'Z') {
                identifierFlags[i] = true;
            } else if (i >= 'a' && i <= 'z') {
                identifierFlags[i] = true;
            } else if (i >= '0' && i <= '9') {
                identifierFlags[i] = true;
            }
        }

        identifierFlags['_'] = true;
    }

    /**
     * 初始化标识符中允许出现的字符的字符串缓存
     */
    private static void initStringCache() {
        for (int i = 0; i < identifierFlags.length; i++) {
            if (identifierFlags[i]) {
                char ch = (char) i;
                stringCache[i] = Character.toString(ch);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Character.charCount(65288));
        System.out.println(Character.charCount(65289));

    }
}