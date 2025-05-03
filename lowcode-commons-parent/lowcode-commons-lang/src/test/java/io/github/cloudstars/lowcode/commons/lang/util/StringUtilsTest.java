package io.github.cloudstars.lowcode.commons.lang.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串工具测试类
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class StringUtilsTest {

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertFalse(StringUtils.isEmpty(" "));
        Assert.assertFalse(StringUtils.isEmpty(" *"));
    }

    @Test
    public void testIsBlank() {
        Assert.assertTrue(StringUtils.isBlank(null));
        Assert.assertTrue(StringUtils.isBlank(""));
        Assert.assertTrue(StringUtils.isBlank(" "));
        Assert.assertFalse(StringUtils.isBlank(" *"));
    }

    @Test
    public void testVariableReplace1() {
        String s = "abc${v1}efg${v2}xyz";
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("v1", "***");
        dataMap.put("v2", 123);

        String result = StringUtils.replaceVariablePlaceholder(s, dataMap);
        Assert.assertEquals("abc***efg123xyz", result);
    }

    @Test
    public void testVariableReplace2() {
        String s = "abc${v1}efg${v2}xyz";
        Map<String, Object> dataMap = new HashMap<>();
        String result = StringUtils.replaceVariablePlaceholder(s, dataMap);
        Assert.assertEquals(s, result);
    }

    @Test
    public void testVariableReplace3() {
        String s = "${v1}${v2}${v4}";
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("v1", "***");
        dataMap.put("v3", 123);
        dataMap.put("v4", null);

        String result = StringUtils.replaceVariablePlaceholder(s, dataMap);
        Assert.assertEquals("***${v2}null", result);
    }

}
