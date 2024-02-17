package net.cf.commons.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4ClassRunner.class)
public class ObjectUtilsTest {

    @Test
    public void test() {
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
    public void testGetDeclaredGetMethodMap() throws Exception {
        T t = new T();
        t.setB(false);
        t.setBb(true);

        Map<String, Method> methodMap = ObjectUtils.getDeclaredGetMethodMap(t);
        Assert.assertTrue(methodMap != null && methodMap.size() == 3);
        Method methodB = methodMap.get("b");
        Assert.assertTrue(methodB != null && methodB.equals(T.class.getDeclaredMethod("isB")));
        Method methodBB = methodMap.get("bb");
        Assert.assertTrue(methodBB != null && methodBB.equals(T.class.getDeclaredMethod("getBb")));
        Method methodS = methodMap.get("s");
        Assert.assertTrue(methodS != null && methodS.equals(T.class.getDeclaredMethod("getS")));
    }

    @Test
    public void testGetProperty() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("aa", "hello");
        dataMap.put("bb", "world");
        Object mapValueAA = ObjectUtils.getPropertyValue(dataMap, "aa");
        Assert.assertTrue("hello".equals(mapValueAA));
        Object mapValueBB = ObjectUtils.getPropertyValue(dataMap, "bb");
        Assert.assertTrue("world".equals(mapValueBB));
        Object mapValueCC = ObjectUtils.getPropertyValue(dataMap, "cc");
        Assert.assertTrue(mapValueCC == null);

        T object = new T();
        object.b = true;
        object.bb = false;
        object.s = "Hello, world!";
        Object tValueB = ObjectUtils.getPropertyValue(object, "b");
        Assert.assertTrue(tValueB instanceof Boolean && (Boolean) tValueB);
        Object tValueBB = ObjectUtils.getPropertyValue(object, "bb");
        Assert.assertTrue(tValueB instanceof Boolean && !((Boolean) tValueBB));
        Object tValueS = ObjectUtils.getPropertyValue(object, "s");
        Assert.assertTrue("Hello, world!".equals(tValueS));
    }

    private class T {

        private boolean b;

        private Boolean bb;

        private String s;

        public boolean isB() {
            return b;
        }

        public void setB(boolean b) {
            this.b = b;
        }

        public Boolean getBb() {
            return bb;
        }

        public void setBb(Boolean bb) {
            this.bb = bb;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }
}
