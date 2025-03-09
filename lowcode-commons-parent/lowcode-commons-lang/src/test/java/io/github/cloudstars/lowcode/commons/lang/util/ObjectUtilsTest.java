package io.github.cloudstars.lowcode.commons.lang.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * JavaObject工具测试类
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class ObjectUtilsTest {

    @Test
    public void testGetFieldValueFromMap() {
        Date now = new Date();
        Map<String, Object> data = new HashMap<>();
        data.put("a", 123);
        data.put("b", now);

        Object av = ObjectUtils.getFieldValue(data, "a");
        Assert.assertEquals(123, av);
        Assert.assertNotEquals(124, av);
        Object bv = ObjectUtils.getFieldValue(data, "b");
        Assert.assertEquals(now, bv);
    }

    @Test
    public void testGetFieldValueFromObject() {
        Date now = new Date();
        TestClass testObject = new TestClass();
        testObject.setStr("文本");
        testObject.setInteger(123);
        testObject.setDate(now);
        testObject.setaBoolean(true);

        Assert.assertEquals("文本", ObjectUtils.getFieldValue(testObject, "str"));
        Assert.assertEquals(123, ObjectUtils.getFieldValue(testObject, "integer"));
        Assert.assertEquals(now, ObjectUtils.getFieldValue(testObject, "date"));
        Assert.assertEquals(true, ObjectUtils.getFieldValue(testObject, "aBoolean"));
    }

    @Test
    public void testCloneMap() {
        Date now = new Date();
        Map<String, Object> fromMap = new HashMap<>();
        fromMap.put("a", "abc");
        fromMap.put("b", now);

        Object toObject = ObjectUtils.clone(fromMap);
        Assert.assertTrue(toObject instanceof Map);
        Map<String, Object> toMap = (Map<String, Object>) toObject;
        Assert.assertEquals("abc", toMap.get("a"));
        Assert.assertEquals(now, toMap.get("b"));
    }

    @Test
    public void testCloneMapWithProps() {
        Date now = new Date();
        Map<String, Object> fromMap = new HashMap<>();
        fromMap.put("a", "abc");
        fromMap.put("b", now);

        Map<String, Object> propMap = new HashMap<>();
        propMap.put("a", "efg");
        propMap.put("c", 1);

        Object toObject = ObjectUtils.clone(fromMap, propMap);
        Assert.assertTrue(toObject instanceof Map);
        Map<String, Object> toMap = (Map<String, Object>) toObject;
        Assert.assertEquals("efg", toMap.get("a"));
        Assert.assertEquals(now, toMap.get("b"));
        Assert.assertNull(toMap.get("c")); // 不存在的属性忽略
    }

    @Test
    public void testCloneObject() {
        Date now = new Date();
        TestClass fromObject = new TestClass();
        fromObject.setStr("abc");
        fromObject.setInteger(1);
        fromObject.setaBoolean(true);
        fromObject.setDate(now);

        Object toObject = ObjectUtils.clone(fromObject);
        Assert.assertTrue(toObject instanceof TestClass);
        TestClass toTestObject = (TestClass) toObject;
        Assert.assertEquals("abc", toTestObject.getStr());
        Assert.assertEquals(Integer.valueOf(1), toTestObject.getInteger());
        Assert.assertEquals(Boolean.TRUE, toTestObject.getaBoolean());
        Assert.assertEquals(now, toTestObject.getDate());
    }

    @Test
    public void testCloneObjectWithProps() {
        Date now = new Date();
        TestClass fromObject = new TestClass();
        fromObject.setStr("abc");
        fromObject.setInteger(1);
        fromObject.setaBoolean(true);
        fromObject.setDate(now);

        Map<String, Object> propMap = new HashMap<>();
        propMap.put("str", "efg");
        propMap.put("c", 1); // 不存在的属性忽略

        Object toObject = ObjectUtils.clone(fromObject, propMap);
        Assert.assertTrue(toObject instanceof TestClass);
        TestClass toTestObject = (TestClass) toObject;
        Assert.assertEquals("efg", toTestObject.getStr());
        Assert.assertEquals(Integer.valueOf(1), toTestObject.getInteger());
        Assert.assertEquals(Boolean.TRUE, toTestObject.getaBoolean());
        Assert.assertEquals(now, toTestObject.getDate());
    }

}
