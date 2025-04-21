package io.github.cloudstars.lowcode.commons.lang.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4ClassRunner.class)
public class ObjectUtilsTest {

    @Test
    public void testGetDeclaredGetMethodMap() throws Exception {
        ObjectUtilsTestClass1 t = new ObjectUtilsTestClass1();
        t.setB(false);
        t.setBb(true);

        Map<String, Method> methodMap = ObjectUtils.getDeclaredGetMethodMap(t);
        Assert.assertTrue(methodMap != null && methodMap.size() == 3);
        Method methodB = methodMap.get("b");
        Assert.assertTrue(methodB != null && methodB.equals(ObjectUtilsTestClass1.class.getDeclaredMethod("isB")));
        Method methodBB = methodMap.get("bb");
        Assert.assertTrue(methodBB != null && methodBB.equals(ObjectUtilsTestClass1.class.getDeclaredMethod("getBb")));
        Method methodS = methodMap.get("s");
        Assert.assertTrue(methodS != null && methodS.equals(ObjectUtilsTestClass1.class.getDeclaredMethod("getS")));
    }

    @Test
    public void testGetFieldNames() {
        ObjectUtilsTestClass2 tc = new ObjectUtilsTestClass2();
        List<String> fieldNames = ObjectUtils.getDeclaredFieldNames(tc);
        String[] fns = new String[]{"str", "integer", "date", "aBoolean"};
        for (int i = 0, l = fns.length; i < l; i++) {
            assert (fieldNames.contains(fns[i]));
        }
    }

    @Test
    public void testIsGeneralValue() {
        Assert.assertTrue(ObjectUtils.isGeneralValue(null));
        Assert.assertTrue(ObjectUtils.isGeneralValue(1));
        Assert.assertTrue(ObjectUtils.isGeneralValue(Integer.valueOf(1)));
        Assert.assertTrue(ObjectUtils.isGeneralValue(1l));
        Assert.assertTrue(ObjectUtils.isGeneralValue(Long.valueOf(1)));
        Assert.assertTrue(ObjectUtils.isGeneralValue(Short.valueOf((short) 1)));
        Assert.assertTrue(ObjectUtils.isGeneralValue(1.1));
        Assert.assertTrue(ObjectUtils.isGeneralValue(Float.valueOf(1.1f)));
        Assert.assertTrue(ObjectUtils.isGeneralValue(1.1d));
        Assert.assertTrue(ObjectUtils.isGeneralValue(Double.valueOf(1.1d)));
        Assert.assertTrue(ObjectUtils.isGeneralValue(BigDecimal.valueOf(123.45)));
        Assert.assertTrue(ObjectUtils.isGeneralValue(true));
        Assert.assertTrue(ObjectUtils.isGeneralValue(Boolean.TRUE));
        Assert.assertTrue(ObjectUtils.isGeneralValue(new Date()));
        Assert.assertTrue(ObjectUtils.isGeneralValue(new java.sql.Date(System.currentTimeMillis())));
        Assert.assertTrue(ObjectUtils.isGeneralValue(new java.sql.Time(1200)));
    }


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
        ObjectUtilsTestClass2 testObject = new ObjectUtilsTestClass2();
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
        ObjectUtilsTestClass2 fromObject = new ObjectUtilsTestClass2();
        fromObject.setStr("abc");
        fromObject.setInteger(1);
        fromObject.setaBoolean(true);
        fromObject.setDate(now);

        Object toObject = ObjectUtils.clone(fromObject);
        Assert.assertTrue(toObject instanceof ObjectUtilsTestClass2);
        ObjectUtilsTestClass2 toTestObject = (ObjectUtilsTestClass2) toObject;
        Assert.assertEquals("abc", toTestObject.getStr());
        Assert.assertEquals(Integer.valueOf(1), toTestObject.getInteger());
        Assert.assertEquals(Boolean.TRUE, toTestObject.getaBoolean());
        Assert.assertEquals(now, toTestObject.getDate());
    }

    @Test
    public void testCloneObjectWithProps() {
        Date now = new Date();
        ObjectUtilsTestClass2 fromObject = new ObjectUtilsTestClass2();
        fromObject.setStr("abc");
        fromObject.setInteger(1);
        fromObject.setaBoolean(true);
        fromObject.setDate(now);

        Map<String, Object> propMap = new HashMap<>();
        propMap.put("str", "efg");
        propMap.put("c", 1); // 不存在的属性忽略

        Object toObject = ObjectUtils.clone(fromObject, propMap);
        Assert.assertTrue(toObject instanceof ObjectUtilsTestClass2);
        ObjectUtilsTestClass2 toTestObject = (ObjectUtilsTestClass2) toObject;
        Assert.assertEquals("efg", toTestObject.getStr());
        Assert.assertEquals(Integer.valueOf(1), toTestObject.getInteger());
        Assert.assertEquals(Boolean.TRUE, toTestObject.getaBoolean());
        Assert.assertEquals(now, toTestObject.getDate());
    }

}
