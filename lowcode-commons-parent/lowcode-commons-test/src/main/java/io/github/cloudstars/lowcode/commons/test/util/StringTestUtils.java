package io.github.cloudstars.lowcode.commons.test.util;

import org.junit.ComparisonFailure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SQL工具类
 *
 * @author clouds
 */
public final class StringTestUtils {

    private final static Logger logger = LoggerFactory.getLogger(StringTestUtils.class);

    private StringTestUtils() {}

    /**
     * 断言两个字符串在忽略空白字符时是否相等
     *
     * @param s1 字符串1
     * @param s2 字符串2
     */
    public static void assertEqualsIgnoreWhiteSpace(final String s1, final String s2) {
        String ts1 = s1.replaceAll("\\s", "");
        String ts2 = s2.replaceAll("\\s", "");
        if (!ts1.equalsIgnoreCase(ts2)) {
            throw new ComparisonFailure("字符串忽略空白字符、大小写不敏感比较失败", s1, s2);
        }
    }

}
