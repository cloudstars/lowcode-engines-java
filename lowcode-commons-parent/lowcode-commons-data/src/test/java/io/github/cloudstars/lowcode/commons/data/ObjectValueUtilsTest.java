package io.github.cloudstars.lowcode.commons.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * ObjectValue工具测试类
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class ObjectValueUtilsTest {

    @Test
    public void test() {
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(null));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(1));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(Integer.valueOf(1)));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(1l));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(Long.valueOf(1)));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(Short.valueOf((short) 1)));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(1.1));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(Float.valueOf(1.1f)));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(1.1d));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(Double.valueOf(1.1d)));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(BigDecimal.valueOf(123.45)));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(true));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(Boolean.TRUE));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(new Date()));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(new java.sql.Date(System.currentTimeMillis())));
        Assert.assertTrue(ObjectValueUtils.isGeneralValue(new java.sql.Time(1200)));
    }

    @Test
    public void testGetFieldValueFromMap() {
        Date now = new Date();
        Map<String, Object> data = new HashMap<>();
        data.put("a", 123);
        data.put("b", now);

        Object av = ObjectValueUtils.getFieldValue(data, "a");
        Assert.assertEquals(123, av);
        Assert.assertNotEquals(124, av);
        Object bv = ObjectValueUtils.getFieldValue(data, "b");
        Assert.assertEquals(now, bv);
    }

    @Test
    public void testGetFieldValueFromObject() {
        Date now = new Date();
        ObjectValueTestClass testObject = new ObjectValueTestClass();
        testObject.setStr("文本");
        testObject.setInteger(123);
        testObject.setDate(now);
        testObject.setaBoolean(true);

        Assert.assertEquals("文本", ObjectValueUtils.getFieldValue(testObject, "str"));
        Assert.assertEquals(123, ObjectValueUtils.getFieldValue(testObject, "integer"));
        Assert.assertEquals(now, ObjectValueUtils.getFieldValue(testObject, "date"));
        Assert.assertEquals(true, ObjectValueUtils.getFieldValue(testObject, "aBoolean"));
    }

    @Test
    public void testCloneMap() {
        Date now = new Date();
        Map<String, Object> fromMap = new HashMap<>();
        fromMap.put("a", "abc");
        fromMap.put("b", now);

        Object toObject = ObjectValueUtils.clone(fromMap);
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

        Object toObject = ObjectValueUtils.clone(fromMap, propMap);
        Assert.assertTrue(toObject instanceof Map);
        Map<String, Object> toMap = (Map<String, Object>) toObject;
        Assert.assertEquals("efg", toMap.get("a"));
        Assert.assertEquals(now, toMap.get("b"));
        Assert.assertNull(toMap.get("c")); // 不存在的属性忽略
    }

    @Test
    public void testCloneObject() {
        Date now = new Date();
        ObjectValueTestClass fromObject = new ObjectValueTestClass();
        fromObject.setStr("abc");
        fromObject.setInteger(1);
        fromObject.setaBoolean(true);
        fromObject.setDate(now);

        Object toObject = ObjectValueUtils.clone(fromObject);
        Assert.assertTrue(toObject instanceof ObjectValueTestClass);
        ObjectValueTestClass toTestObject = (ObjectValueTestClass) toObject;
        Assert.assertEquals("abc", toTestObject.getStr());
        Assert.assertEquals(Integer.valueOf(1), toTestObject.getInteger());
        Assert.assertEquals(Boolean.TRUE, toTestObject.getaBoolean());
        Assert.assertEquals(now, toTestObject.getDate());
    }

    @Test
    public void testCloneObjectWithProps() {
        Date now = new Date();
        ObjectValueTestClass fromObject = new ObjectValueTestClass();
        fromObject.setStr("abc");
        fromObject.setInteger(1);
        fromObject.setaBoolean(true);
        fromObject.setDate(now);

        Map<String, Object> propMap = new HashMap<>();
        propMap.put("str", "efg");
        propMap.put("c", 1); // 不存在的属性忽略

        Object toObject = ObjectValueUtils.clone(fromObject, propMap);
        Assert.assertTrue(toObject instanceof ObjectValueTestClass);
        ObjectValueTestClass toTestObject = (ObjectValueTestClass) toObject;
        Assert.assertEquals("efg", toTestObject.getStr());
        Assert.assertEquals(Integer.valueOf(1), toTestObject.getInteger());
        Assert.assertEquals(Boolean.TRUE, toTestObject.getaBoolean());
        Assert.assertEquals(now, toTestObject.getDate());
    }

}
