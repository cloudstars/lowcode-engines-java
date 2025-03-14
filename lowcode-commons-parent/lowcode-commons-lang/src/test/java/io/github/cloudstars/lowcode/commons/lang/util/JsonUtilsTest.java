package io.github.cloudstars.lowcode.commons.lang.util;

import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

/**
 * JSON工具测试类
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class JsonUtilsTest {

    @Test
    public void testToJsonString() {
        TestClass testObject = new TestClass();
        testObject.setDate(new Date(1741520837469L));
        testObject.setStr("abc");
        String expectedJsonString = FileTestUtils.loadTextFromClasspath("util/test1.json");
        JsonTestUtils.assertObjectEquals(expectedJsonString, JsonUtils.toJsonString(testObject));
    }

}
