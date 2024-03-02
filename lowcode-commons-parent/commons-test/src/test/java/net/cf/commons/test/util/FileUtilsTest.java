package net.cf.commons.test.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;

@RunWith(JUnit4.class)
public class FileUtilsTest {

    @Test
    public void testLoadTextFromClassPath() {
        String content = FileTestUtils.loadTextFromClasspath("files/file1.txt");
        Assert.assertTrue("{\"abc\":  \"xyz\"}".equals(content));
    }

    @Test
    public void testLoadTextsFromClassPath() {
        Map<String, String> contents = FileTestUtils.loadTextsFromClasspath("json/*.json");
        Assert.assertTrue(contents != null && contents.size() == 2);
        String mapFileContent = contents.get("map.json");
        Assert.assertTrue(mapFileContent != null);
        Assert.assertTrue(JSONObject.parseObject(mapFileContent).size() == 2);
        String listFileContent = contents.get("list.json");
        Assert.assertTrue(listFileContent != null);
        Assert.assertTrue(JSONArray.parseArray(listFileContent).size() == 3);
    }

    @Test
    public void testLoadTextFromLocalPath() {
        // 程序运行时默认的路径是项目根目录，所以可以读取到pom.xml
        String content = FileTestUtils.loadTextFromLocalPath("./pom.xml");
        Assert.assertTrue(content != null && content.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
    }


}
