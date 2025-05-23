package net.cf.object.engine.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class ObjectBeanUtilsTest {

    @Test
    public void testGetDeclaredGetMethodMap() throws Exception {
        T t = new T();
        t.setB(false);
        t.setBb(true);

        Map<String, Method> methodMap = ObjectBeanUtils.getDeclaredGetMethodMap(t);
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
        Object mapValueAA = ObjectBeanUtils.getPropertyValue(dataMap, "aa");
        Assert.assertTrue("hello".equals(mapValueAA));
        Object mapValueBB = ObjectBeanUtils.getPropertyValue(dataMap, "bb");
        Assert.assertTrue("world".equals(mapValueBB));
        Object mapValueCC = ObjectBeanUtils.getPropertyValue(dataMap, "cc");
        Assert.assertTrue(mapValueCC == null);

        T object = new T();
        object.b = true;
        object.bb = false;
        object.s = "Hello, world!";
        Object tValueB = ObjectBeanUtils.getPropertyValue(object, "b");
        Assert.assertTrue(tValueB instanceof Boolean && (Boolean) tValueB);
        Object tValueBB = ObjectBeanUtils.getPropertyValue(object, "bb");
        Assert.assertTrue(tValueB instanceof Boolean && !((Boolean) tValueBB));
        Object tValueS = ObjectBeanUtils.getPropertyValue(object, "s");
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
