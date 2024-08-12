package net.cf.commons.test.util;

import org.junit.Assert;
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
     * 判断两个字符串在忽略空白字符时是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equalsIgnoreWhiteSpace(final String s1, final String s2) {
        String ts1 = s1.replaceAll("\\s", "");
        String ts2 = s2.replaceAll("\\s", "");
        Assert.assertEquals(ts1, ts2);
        return ts1.equalsIgnoreCase(ts2);
    }

    /**
     * 断言两个字符串在忽略空白字符时相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static void assertEqualsIgnoreWhiteSpace(final String s1, final String s2) {
        String ts1 = s1.replaceAll("\\s", "");
        String ts2 = s2.replaceAll("\\s", "");
        Assert.assertEquals(ts1, ts2);
    }
}
