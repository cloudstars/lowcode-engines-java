package net.cf.commons.test.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;

@RunWith(JUnit4.class)
public class DataCompareUtilsTest {

    @Test
    public void testIsAssignableFromObjectByObject() {
        long ts = System.currentTimeMillis();
        SourceClass sourceObj = new SourceClass();
        sourceObj.a = "a";
        sourceObj.b = true;
        sourceObj.d = new Date(ts);
        sourceObj.f = 1.23f;
        sourceObj.t = new Time(123);
        sourceObj.i = 123;

        DataCompareUtilsTest.TargetClass targetObj = new DataCompareUtilsTest.TargetClass();
        targetObj.a = "a";
        targetObj.b = true;
        targetObj.d = new Date(ts);
        targetObj.f = 1.23f;
        targetObj.t = new Time(123);

        // target里少一个属性
        Assert.assertFalse(DataCompareTestUtils.isAssignableFromObject(sourceObj, targetObj));

        // target补上缺的那一个属性
        targetObj.i = 123;
        Assert.assertTrue(DataCompareTestUtils.isAssignableFromObject(sourceObj, targetObj));

        // target再加上一些新的属性，不影响结果
        targetObj.l = 123l;
        targetObj.s = 123;
        Assert.assertTrue(DataCompareTestUtils.isAssignableFromObject(sourceObj, targetObj));

        // 给source添加一个子对象
        sourceObj.sub = new SourceSubClass("123", new BigDecimal("123.45"));
        Assert.assertFalse(DataCompareTestUtils.isAssignableFromObject(sourceObj, targetObj));

        // 给target添加一个子对象
        targetObj.sub = new DataCompareUtilsTest.TargetSubClass("123", new BigDecimal("123.45"));
        Assert.assertTrue(DataCompareTestUtils.isAssignableFromObject(sourceObj, targetObj));
    }


    @Test
    public void testByMap() {
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("a", "123");
        sourceMap.put("b", "xyz");
        Map<String, Object> targetMap = new HashMap<>();
        targetMap.put("a", "123");

        List<String> fieldNamesA = Arrays.asList("a");
        List<String> fieldNamesAB = Arrays.asList("a", "b");
        List<String> fieldNamesABC = Arrays.asList("a", "b", "c");

        Assert.assertFalse(DataCompareTestUtils.isAssignableFromObject(sourceMap, targetMap));
        Assert.assertFalse(DataCompareTestUtils.isAssignableFromMap(sourceMap, targetMap));
        Assert.assertTrue(DataCompareTestUtils.isAssignableFromMapWithProperties(sourceMap, targetMap, fieldNamesA));
        Assert.assertFalse(DataCompareTestUtils.isAssignableFromMapWithProperties(sourceMap, targetMap, fieldNamesAB));
        Assert.assertFalse(DataCompareTestUtils.isAssignableFromMapWithProperties(sourceMap, targetMap, fieldNamesABC));

        targetMap.put("b", "xyz");
        Assert.assertTrue(DataCompareTestUtils.isAssignableFromObject(sourceMap, targetMap));
        Assert.assertTrue(DataCompareTestUtils.isAssignableFromMap(sourceMap, targetMap));

        targetMap.put("c", "***");
        Assert.assertTrue(DataCompareTestUtils.isAssignableFromObject(sourceMap, targetMap));
        Assert.assertTrue(DataCompareTestUtils.isAssignableFromMap(sourceMap, targetMap));
    }


    private class TargetClass {
        public String a;
        public Boolean b;
        public Integer i;
        public Date d;
        public Float f;
        public Time t;
        public Long l;
        public Short s;
        public TargetSubClass sub;
    }


    private class TargetSubClass {
        public String sa;
        public BigDecimal bd;

        public TargetSubClass(String sa, BigDecimal bd) {
            this.sa = sa;
            this.bd = bd;
        }
    }

    private class SourceClass {
        public String a;
        public Boolean b;
        public Integer i;
        public Date d;
        public Float f;
        public Time t;
        public SourceSubClass sub;
    }

    private class SourceSubClass {
        public String sa;
        public BigDecimal bd;

        public SourceSubClass(String sa, BigDecimal bd) {
            this.sa = sa;
            this.bd = bd;
        }
    }

}
