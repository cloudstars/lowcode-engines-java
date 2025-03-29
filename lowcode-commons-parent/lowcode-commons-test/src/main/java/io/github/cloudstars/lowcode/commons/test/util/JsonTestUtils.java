package io.github.cloudstars.lowcode.commons.test.util;

import com.alibaba.fastjson.JSON;
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
     * @return Map
     */
    @Deprecated
    public static Map<String, Object> loadMapFromClasspath(String filePath) {
        String content = FileTestUtils.loadTextFromClasspath(filePath);
        return JSONObject.parseObject(content, HashMap.class);
    }

    /**
     * 从类路径加载JSON文件并反序列化成Map
     *
     * @param filePath
     * @return List
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
     * @return List
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
     * @param jsonObject
     * @return Map
     */
    private static Map<String, Object> toMap(JSONObject jsonObject) {
        Map<String, Object> dataMap = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
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
    @Deprecated
    public static void assertObjectEquals(Object expected, Object actual) {
        Assert.assertFalse(expected == null && actual != null);
        Assert.assertFalse(expected != null && actual == null);
        String expectedJsonString = JSONObject.toJSONString(expected, SerializerFeature.PrettyFormat);
        String actualJsonString = JSONObject.toJSONString(actual, SerializerFeature.PrettyFormat);
        Assert.assertEquals(expectedJsonString, actualJsonString);
    }

    /**
     * 判断源对象是否由目标对象派生的
     *
     * @param expectedJsonString 预期的JSON字符串
     * @param actualJsonString   事实的JSON字符串
     */
    public static void assertDerivedFrom(String expectedJsonString, String actualJsonString) {
        Assert.assertFalse(expectedJsonString == null && actualJsonString != null);
        Assert.assertFalse(expectedJsonString != null && actualJsonString == null);

        assertDerivedFrom(JSON.parse(expectedJsonString), JSON.parse(actualJsonString), "root");
    }

    /**
     * 判断源对象是否由目标对象派生的
     *
     * @param expected 预期对象
     * @param actual   事实对象
     */
    private static void assertDerivedFrom(Object expected, Object actual, String jsonPath) {
        if (expected instanceof JSONObject && actual instanceof JSONObject) {
            assertDerivedFromObject((JSONObject) expected, (JSONObject) actual, jsonPath);
        } else if (expected instanceof JSONArray && actual instanceof JSONArray) {
            assertDerivedFromArray((JSONArray) expected, (JSONArray) actual, jsonPath);
        } else {
            String prefix = jsonPath + ":";
            Assert.assertEquals(prefix + expected, prefix + actual);
        }
    }

    /**
     * 断言预期的对象是否从事实的对象中派生
     *
     * @param expectedObject 预期对象
     * @param actualObject   事实对象
     */
    private static void assertDerivedFromObject(JSONObject expectedObject, JSONObject actualObject, String jsonPath) {
        expectedObject.forEach((k, v) -> {
            assertDerivedFrom(v, actualObject.get(k), jsonPath + "->" + k);
        });
    }

    /**
     * 断言预期的列表是否从事实的列表中派生
     *
     * @param expectedArray 预期列表
     * @param actualArray   事实列表
     */
    private static void assertDerivedFromArray(JSONArray expectedArray, JSONArray actualArray, String jsonPath) {
        int el = expectedArray.size();
        int al = actualArray.size();
        Assert.assertFalse(el > al);

        for (int i = 0; i < el; i++) {
            assertDerivedFrom(expectedArray.get(i), actualArray.get(i), jsonPath + "->[" + i + "]");
        }
    }

}
