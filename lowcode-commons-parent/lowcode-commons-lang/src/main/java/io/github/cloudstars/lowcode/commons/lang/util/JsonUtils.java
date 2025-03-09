package io.github.cloudstars.lowcode.commons.lang.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON工具类
 *
 * @author clouds
 */
public class JsonUtils {

    /**
     *
     * @param jsonString
     * @return
     */
    public static JsonObject parseObject(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return new JsonObject(jsonObject);
    }

    /**
     *
     * @param jsonString
     * @return
     */
    public static JsonArray parseArray(String jsonString) {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        return new JsonArray(jsonArray);
    }

}
