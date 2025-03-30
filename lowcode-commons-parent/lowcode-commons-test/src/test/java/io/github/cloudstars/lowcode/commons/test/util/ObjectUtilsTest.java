package io.github.cloudstars.lowcode.commons.test.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class ObjectUtilsTest {

    @Test
    public void testGetFieldNames() {
        TestClass tc = new TestClass();
        List<String> fieldNames = ObjectTestUtils.getDeclaredFieldNames(tc);
        String[] fns = new String[]{"str", "integer", "date", "aBoolean"};
        for (int i = 0, l = fns.length; i < l; i++) {
            assert (fieldNames.contains(fns[i]));
        }
    }

}
