package io.github.cloudstars.lowcode.commons.lang.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FileUtilsTest {

    @Test
    public void testLoadTextFromClassPath() {
        String content = FileUtils.loadTextFromClasspath("files/file1.txt");
        Assert.assertTrue("{\"abc\":  \"xyz\"}".equals(content));
    }

}
