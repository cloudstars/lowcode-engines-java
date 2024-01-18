package com.cmbchina.codefriend.commons.test.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmbchina.codefriend.commons.util.FileUtils;
import com.cmbchina.codefriend.commons.util.JsonUtils;

import java.sql.Time;
import java.util.*;

/**
 * JSON 工具类
 *
 * @author 80274507
 */
public final class JsonTestUtils {

    private JsonTestUtils() {
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
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isJSONObjectAssignableFrom(JSONObject source, JSONObject target) {
        if (source == null) {
            return true;
        }

        if (target == null) {
            return false;
        }

        if (source.size() > target.size()) {
            return false;
        }

        for (Map.Entry<String, Object> entry : source.entrySet()) {
            Object svi = source.get(entry.getKey());
            Object tvi = target.get(entry.getKey());
            if (!JsonTestUtils.isObjectAssignableFrom(svi, tvi)) {
                return false;
            }
        }

        return true;
    }


    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isObjectAssignableFrom(Object source, Object target) {
        if (source == null) {
            return true;
        }

        if (target == null) {
            return false;
        }

        // 只要有一个是基础类型就直接比较
        if (isGeneralValue(source) || isGeneralValue(target)) {
            return source.equals(target);
        }

        // 两个都是对象类型时
        Object sourceJson = JSON.parse(JSON.toJSONString(source));
        Object targetJson = JSON.parse(JSON.toJSONString(target));
        if (sourceJson instanceof JSONObject && targetJson instanceof JSONObject) {
            return JsonTestUtils.isJSONObjectAssignableFrom((JSONObject) sourceJson, (JSONObject) targetJson);
        } else if (sourceJson instanceof JSONArray && targetJson instanceof JSONArray) {
            return JsonTestUtils.isJSONArrayAssignableFrom((JSONArray) sourceJson, (JSONArray) targetJson);
        } else {
            return sourceJson.equals(targetJson);
        }
    }



    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isJSONArrayAssignableFrom(JSONArray source, JSONArray target) {
        return JsonTestUtils.isListAssignableFrom(source, target);
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isListAssignableFrom(List source, List target) {
        if (source == null) {
            return true;
        }

        if (target == null) {
            return false;
        }

        if (source.size() > target.size()) {
            return false;
        }

        for (int i = 0, l = source.size(); i < l; i++) {
            Object svi = source.get(i);
            Object tvi = target.get(i);
            if (!JsonTestUtils.isObjectAssignableFrom(svi, tvi)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 是否基础类型的值
     *
     * @param value
     */
    private static boolean isGeneralValue(Object value) {
        return (value == null || value instanceof String || value instanceof Number || value instanceof Date || value instanceof Boolean || value instanceof Time);
    }
}
