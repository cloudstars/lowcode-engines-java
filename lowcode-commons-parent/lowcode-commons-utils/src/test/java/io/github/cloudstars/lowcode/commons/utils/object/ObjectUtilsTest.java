package io.github.cloudstars.lowcode.commons.utils.object;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;
import java.util.Map;

@RunWith(JUnit4ClassRunner.class)
public class ObjectUtilsTest {

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
