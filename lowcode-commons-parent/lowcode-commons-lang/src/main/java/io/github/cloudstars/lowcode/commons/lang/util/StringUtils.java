package io.github.cloudstars.lowcode.commons.lang.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author clouds
 */
public final class StringUtils {

    /**
     * 变量占位符模式
     */
    private static final Pattern VAR_PATTERN = Pattern.compile("\\$\\{([\\w_]+)\\}");

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

    /**
     * 替换字符串中的变量占位符
     *
     * @param s       输入字符串
     * @param dataMap 输入数据
     * @return 替换后的字符串
     */
    public static String replaceVariablePlaceholder(String s, Map<String, Object> dataMap) {
        if (s == null || s.length() == 0 || dataMap == null || dataMap.isEmpty()) {
            return s;
        }

        StringBuffer stringBuffer = null;
        Matcher matcher = VAR_PATTERN.matcher(s);
        while (matcher.find()) {
            String variable = matcher.group(1);
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            if (dataMap.containsKey(variable)) {
                Object replacement = dataMap.get(variable);
                if (replacement != null) {
                    matcher.appendReplacement(stringBuffer, replacement.toString());
                } else {
                    matcher.appendReplacement(stringBuffer, "null");
                }
            } else {
                matcher.appendReplacement(stringBuffer, "\\$\\{" + variable + "\\}");
            }
        }

        if (stringBuffer != null && stringBuffer.length() > 0) {
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        }

        return s;
    }

}
