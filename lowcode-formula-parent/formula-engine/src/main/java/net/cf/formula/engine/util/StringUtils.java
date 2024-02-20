package net.cf.formula.engine.util;

public class StringUtils {

    /**
     * 是否是一个数字
     *
     * @param chars
     * @return
     */
    public static boolean isNumber(char[] chars) {
        if (chars == null || chars.length == 0) {
            return false;
        }

        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        int start = chars[0] == '-' ? 1 : 0;
        int i;
        char ch;
        if (sz > start + 1 && chars[start] == '0' && chars[start + 1] == 'x') {
            i = start + 2;
            if (i == sz) {
                return false;
            } else {
                while (i < chars.length) {
                    ch = chars[i];
                    if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f') && (ch < 'A' || ch > 'F')) {
                        return false;
                    }

                    ++i;
                }

                return true;
            }
        } else {
            --sz;

            for (i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                ch = chars[i];
                if (ch >= '0' && ch <= '9') {
                    foundDigit = true;
                    allowSigns = false;
                } else if (ch == '.') {
                    if (hasDecPoint || hasExp) {
                        return false;
                    }

                    hasDecPoint = true;
                } else if (ch != 'e' && ch != 'E') {
                    if (ch != '+' && ch != '-') {
                        return false;
                    }

                    if (!allowSigns) {
                        return false;
                    }

                    allowSigns = false;
                    foundDigit = false;
                } else {
                    if (hasExp) {
                        return false;
                    }

                    if (!foundDigit) {
                        return false;
                    }

                    hasExp = true;
                    allowSigns = true;
                }
            }

            if (i < chars.length) {
                ch = chars[i];
                if (ch >= '0' && ch <= '9') {
                    return true;
                } else if (ch != 'e' && ch != 'E') {
                    if (allowSigns || ch != 'd' && ch != 'D' && ch != 'f' && ch != 'F') {
                        if (ch != 'l' && ch != 'L') {
                            return ch == '.';
                        } else {
                            return foundDigit && !hasExp;
                        }
                    } else {
                        return foundDigit;
                    }
                } else {
                    return false;
                }
            } else {
                return !allowSigns && foundDigit;
            }
        }
    }
}
