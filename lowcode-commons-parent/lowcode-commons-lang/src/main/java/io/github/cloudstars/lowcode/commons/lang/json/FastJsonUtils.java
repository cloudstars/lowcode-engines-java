package io.github.cloudstars.lowcode.commons.lang.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * FastJSON工具
 *
 * @author clouds
 */
class FastJsonUtils {

    /**
     * 将FastJSON对象转为统一封装的JSON对象
     *
     * @param value
     * @return
     */
    public static Object wrap(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof JSONObject) {
            return wrapJSONObject((JSONObject) value);
        }

        if (value instanceof JSONArray) {
            return wrapJSONArray((JSONArray) value);
        }

        return value;
    }

    /**
     * 包裹一个JSON对象
     *
     * @param jsonObject
     * @return 包裹后的JSON对象（会去除FastJSON类型）
     */
    public static JsonObject wrapJSONObject(JSONObject jsonObject) {
        JsonObject wrappedJsonObject = new JsonObject();
        jsonObject.forEach((k, v) -> {
            if (v instanceof JSONObject) {
                wrappedJsonObject.put(k, wrapJSONObject((JSONObject) v));
            } else if (v instanceof JSONArray) {
                wrappedJsonObject.put(k, wrapJSONArray((JSONArray) v));
            } else {
                wrappedJsonObject.put(k, v);
            }
        });

        return wrappedJsonObject;
    }

    /**
     * 包裹一个JSON数组
     *
     * @param jsonArray
     * @return 包裹后的JSON数组（会去除FastJSON类型）
     */
    public static JsonArray wrapJSONArray(JSONArray jsonArray) {
        JsonArray wrappedJsonArray = new JsonArray();
        jsonArray.forEach((item) -> {
            if (item instanceof JSONObject) {
                wrappedJsonArray.add(wrapJSONObject((JSONObject) item));
            } else if (item instanceof JSONArray) {
                wrappedJsonArray.add(wrapJSONArray((JSONArray) item));
            } else {
                wrappedJsonArray.add(item);
            }
        });

        return wrappedJsonArray;
    }
}
