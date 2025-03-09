package io.github.cloudstars.lowcode.commons.test.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 *
 * @author clouds
 */
public final class JsonTestUtils {

    private JsonTestUtils() {
    }

    /**
     * 从类路径加载JSON文件并反序列化成Map
     *
     * @param filePath
     * @return
     */
    public static Map<String, Object> loadMapFromClasspath(String filePath) {
        String content = FileTestUtils.loadTextFromClasspath(filePath);
        return JSONObject.parseObject(content, HashMap.class);
    }

    /**
     * 从类路径加载JSON文件并反序列化成Map
     *
     * @param filePath
     * @return
     */
    public static List<Map<String, Object>> loadListFromClasspath(String filePath) {
        String content = FileTestUtils.loadTextFromClasspath(filePath);
        JSONArray jsonArray = JSONArray.parseArray(content);
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0, l = jsonArray.size(); i < l; i++) {
            String currContent = JSONObject.toJSONString(jsonArray.get(i));
            list.add(JSONObject.parseObject(currContent, HashMap.class));
        }

        return list;
    }

    /**
     * 解析JSON数组
     *
     * @param arrayValue
     * @return
     */
    public static List<Map<String, Object>> toMapList(JSONArray arrayValue) {
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        for (int i = 0; i < arrayValue.size(); i++) {
            Map<String, Object> dataMap = JsonTestUtils.toMap(arrayValue.getJSONObject(i));
            dataMapList.add(dataMap);
        }

        return dataMapList;
    }

    /**
     * 解析JSON对象
     *
     * @param jsonData
     * @return
     */
    private static Map<String, Object> toMap(JSONObject jsonData) {
        Map<String, Object> dataMap = new HashMap<>();
        for (String key : jsonData.keySet()) {
            Object value = jsonData.get(key);
            if (value instanceof JSONArray) {
                JSONArray arrayValue = (JSONArray) value;
                value = JsonTestUtils.toMapList(arrayValue);
            } else if (value instanceof JSONObject) {
                value = value.toString();
            }
            dataMap.put(key, value);
        }

        return dataMap;
    }

    /**
     * 通过JSON格式对比两个对象是否相等
     *
     * @param expected
     * @param actual
     */
    public static void assertObjectEquals(Object expected, Object actual) {
        Assert.assertFalse(expected == null && actual != null);
        Assert.assertFalse(expected != null && actual == null);
        String expectedJsonString = JSONObject.toJSONString(expected, SerializerFeature.PrettyFormat);
        String actualJsonString = JSONObject.toJSONString(expected, SerializerFeature.PrettyFormat);
        Assert.assertEquals(expectedJsonString, actualJsonString);
    }

}
