package io.github.cloudstars.lowcode.commons.lang.util;

/**
 * 字符串工具类
 *
 * @author clouds
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * 是否空字符串
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
       return s == null || s.length() == 0;
    }

    /**
     * 是否空字符串
     *
     * @param s
     * @return
     */
    public static boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }

}
