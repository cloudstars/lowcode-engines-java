package io.github.cloudstars.lowcode.commons.utils.json;

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

        if (value instanceof JSONArray) {
            return new JsonArray((JSONArray) value);
        }
        if (value instanceof JSONObject) {
            return new JsonObject(((JSONObject) value));
        }

        return value;
    }

}
