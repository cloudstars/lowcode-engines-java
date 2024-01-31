package net.cf.commons.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4ClassRunner.class)
public class JsonUtilsTest {

    @Test
    public void testLoadMap() {
        Map<String, Object> dataMap = JsonUtils.loadMapFromClasspath("json/map.json");
        Assert.assertTrue(dataMap != null && dataMap.size() == 2);
        Assert.assertTrue("xyz".equals(dataMap.get("abc")));
        Assert.assertTrue("wlz".equals(dataMap.get("efg")));
    }


    @Test
    public void testLoadList() {
        List<Map<String, Object>> dataMapList = JsonUtils.loadListFromClasspath("json/list.json");
        Assert.assertTrue(dataMapList != null && dataMapList.size() == 3);
        Assert.assertTrue("123".equals(dataMapList.get(2).get("f4")));
    }

    @Test
    public void testIsObjectAssignableFromByMap() {
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("a", "123");
        sourceMap.put("b", "xyz");
        Map<String, Object> targetMap = new HashMap<>();
        targetMap.put("a", "123");
        Assert.assertFalse(JsonUtils.isAssignableFromObject(sourceMap, targetMap));

        targetMap.put("b", "xyz");
        Assert.assertTrue(JsonUtils.isAssignableFromObject(sourceMap, targetMap));
        targetMap.put("c", "***");
        Assert.assertTrue(JsonUtils.isAssignableFromObject(sourceMap, targetMap));
    }

    @Test
    public void testIsObjectAssignableFromByObject() {
        long ts = System.currentTimeMillis();
        SourceClass sourceObj = new SourceClass();
        sourceObj.a = "a";
        sourceObj.b = true;
        sourceObj.d = new Date(ts);
        sourceObj.f = 1.23f;
        sourceObj.t = new Time(123);
        sourceObj.i = 123;

        TargetClass targetObj = new TargetClass();
        targetObj.a = "a";
        targetObj.b = true;
        targetObj.d = new Date(ts);
        targetObj.f = 1.23f;
        targetObj.t = new Time(123);

        // target里少一个属性
        Assert.assertFalse(JsonUtils.isAssignableFromObject(sourceObj, targetObj));

        // target补上缺的那一个属性
        targetObj.i = 123;
        Assert.assertTrue(JsonUtils.isAssignableFromObject(sourceObj, targetObj));

        // target再加上一些新的属性，不影响结果
        targetObj.l = 123l;
        targetObj.s = 123;
        Assert.assertTrue(JsonUtils.isAssignableFromObject(sourceObj, targetObj));

        // 给source添加一个子对象
        sourceObj.sub = new SourceSubClass("123", new BigDecimal("123.45"));
        Assert.assertFalse(JsonUtils.isAssignableFromObject(sourceObj, targetObj));

        // 给target添加一个子对象
        targetObj.sub = new TargetSubClass("123", new BigDecimal("123.45"));
        Assert.assertTrue(JsonUtils.isAssignableFromObject(sourceObj, targetObj));
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
}
