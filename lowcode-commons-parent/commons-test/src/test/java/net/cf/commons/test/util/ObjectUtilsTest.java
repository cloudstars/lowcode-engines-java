package net.cf.commons.test.util;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

@RunWith(JUnit4ClassRunner.class)
public class ObjectUtilsTest {

    @Test
    public void testGetFieldNames() {
        TestClass tc = new TestClass();
        List<String> fieldNames = ObjectUtils.getFieldNames(tc);
        String[] fns = new String[]{"s", "b", "pb", "iI", "ii", "d"};
        for (int i = 0, l = fns.length; i < l; i++) {
            assert (fieldNames.contains(fns[i]));
        }
    }

    private class TestClass {
        private String s;

        private Boolean b;

        private boolean pb;

        private Integer iI;

        private int ii;

        private Date d;

        private class Ti {
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public Boolean getB() {
            return b;
        }

        public void setB(Boolean b) {
            this.b = b;
        }

        public boolean isPb() {
            return pb;
        }

        public void setPb(boolean pb) {
            this.pb = pb;
        }

        public Integer getiI() {
            return iI;
        }

        public void setiI(Integer iI) {
            this.iI = iI;
        }

        public int getIi() {
            return ii;
        }

        public void setIi(int ii) {
            this.ii = ii;
        }

        public Date getD() {
            return d;
        }

        public void setD(Date d) {
            this.d = d;
        }
    }
}
