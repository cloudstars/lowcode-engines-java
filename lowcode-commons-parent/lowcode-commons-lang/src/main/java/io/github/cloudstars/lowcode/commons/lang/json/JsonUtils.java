package io.github.cloudstars.lowcode.commons.lang.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.cloudstars.lowcode.commons.lang.util.FileUtils;

import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 *
 * @author clouds
 */
public final class JsonUtils {

    private JsonUtils() {
    }


    /**
     * 从类路径加载JSON文件并反序列化成JsonObject
     *
     * @param filePath
     * @return JsonObject
     */
    public static JsonObject loadJsonObjectFromClasspath(String filePath) {
        String content = FileUtils.loadTextFromClasspath(filePath);
        JSONObject jsonObject = JSONObject.parseObject(content);
        return FastJsonUtils.wrapJSONObject(jsonObject);
    }

    /**
     * 从类路径加载JSON文件并反序列化成JsonArray
     *
     * @param filePath
     * @return JsonArray
     */
    public static JsonArray loadJsonArrayFromClasspath(String filePath) {
        String content = FileUtils.loadTextFromClasspath(filePath);
        JSONArray jsonArray = JSONArray.parseArray(content);
        return FastJsonUtils.wrapJSONArray(jsonArray);
    }

    /**
     * 将JSON字符串转换为JSON对象
     *
     * @param jsonString JSON字符串
     * @return JSON对象
     */
    public static JsonObject toJsonObject(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return FastJsonUtils.wrapJSONObject(jsonObject);
    }

    /**
     * 将JSON字符串转换JSON数组
     *
     * @param jsonString JSON字符串
     * @return JSON数组
     */
    public static JsonArray toJsonArray(String jsonString) {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        return FastJsonUtils.wrapJSONArray(jsonArray);
    }

    /**
     * 将JSON字符串转换Java对象
     *
     * @param jsonString
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends Object> T parseObject(String jsonString, Class<T> tClass) {
        return JSONObject.parseObject(jsonString, tClass);
    }

    /**
     * 将JSON字符串转换Java对象数组
     *
     * @param jsonString
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends Object> List<T> toJsonArray(String jsonString, Class<T> tClass) {
        return JSONObject.parseArray(jsonString, tClass);
    }

    /**
     * 将Map转换JSON字符串
     *
     * @param dataMap 数据
     * @return JSON字符串
     */
    public static String toJsonString(Map<String, Object> dataMap) {
        return JSON.toJSONString(dataMap);
    }

    /**
     * 将Object转换JSON字符串
     *
     * @param dataObject 数据
     * @return JSON字符串
     */
    public static String toJsonString(Object dataObject) {
        return JSON.toJSONString(dataObject);
    }

}
