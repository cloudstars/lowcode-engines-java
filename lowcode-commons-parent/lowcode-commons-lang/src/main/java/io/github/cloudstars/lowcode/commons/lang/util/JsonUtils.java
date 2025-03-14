package io.github.cloudstars.lowcode.commons.lang.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
     * 将JSON字符串转换为JSON对象
     *
     * @param js JSON字符串
     * @return JSON对象
     */
    public static JsonObject parseObject(String js) {
        JSONObject jsonObject = JSONObject.parseObject(js);
        return new JsonObject(jsonObject);
    }

    /**
     * 将JSON字符串转换JSON数组
     *
     * @param jsonString JSON字符串
     * @return JSON数组
     */
    public static JsonArray parseArray(String jsonString) {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        return new JsonArray(jsonArray);
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
