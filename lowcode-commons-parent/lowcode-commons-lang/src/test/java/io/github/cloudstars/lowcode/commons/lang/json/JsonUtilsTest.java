package io.github.cloudstars.lowcode.commons.lang.json;

import com.alibaba.fastjson.JSONObject;
import io.github.cloudstars.lowcode.commons.lang.util.FileUtils;
import org.junit.Assert;
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

    /**
     * 测试解析对象转JSON字符串
     */
    @Test
    public void testToJsonString() {
        JsonTestClass testObject = new JsonTestClass();
        testObject.setDate(new Date(1741520837469L));
        testObject.setStr("abc");
        String expectedJsonString = FileUtils.loadTextFromClasspath("json/test1.json");
        JSONObject o = JSONObject.parseObject(expectedJsonString);
        Assert.assertEquals(o.get("str"), testObject.getStr());
        Assert.assertEquals(o.get("date"), testObject.getDate().getTime());
    }

    /**
     * 测试解析JSON对象
     */
    @Test
    public void testLoadJsonObject() {
        String jsonObjectStr = FileUtils.loadTextFromClasspath("json/map.json");
        JsonObject jsonObject = JsonUtils.toJsonObject(jsonObjectStr);
        Assert.assertNotNull(jsonObject);
        Assert.assertEquals(2, jsonObject.size());
        jsonObject.forEach((k, v) -> {
            if ("abc".equals(k)) {
                Assert.assertEquals("xyz", v);
            } else if ("efg".equals(k)) {
                Assert.assertEquals("wlz", v);
            } else {
                Assert.assertFalse(false);
            }
        });
    }


    /**
     * 测试解析JSON数组
     */
    @Test
    public void testLoadJsonArray() {
        String jsonArrayStr = FileUtils.loadTextFromClasspath("json/list.json");
        JsonArray jsonArray = JsonUtils.toJsonArray(jsonArrayStr);
        Assert.assertNotNull(jsonArray);
        Assert.assertEquals(3, jsonArray.size());
        jsonArray.forEach((item) -> {
            Assert.assertEquals(JsonObject.class, item.getClass());
        });
    }

}
