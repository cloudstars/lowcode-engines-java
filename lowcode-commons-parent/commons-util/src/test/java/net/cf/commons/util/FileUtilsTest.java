package net.cf.commons.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.Map;

@RunWith(JUnit4ClassRunner.class)
public class FileUtilsTest {

    @Test
    public void testLoadTextFromClassPath() {
        String content = FileUtils.loadTextFromClasspath("files/file1.txt");
        Assert.assertTrue("{\"abc\":  \"xyz\"}".equals(content));
    }

    @Test
    public void testLoadTextsFromClassPath() {
        Map<String, String> contents = FileUtils.loadTextsFromClasspath("json/*.json");
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
        String content = FileUtils.loadTextFromLocalPath("./pom.xml");
        Assert.assertTrue(content != null && content.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
    }


}
