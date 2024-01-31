package net.cf.commons.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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
     * 从类路径加载JSON文件并反序列化成Map
     *
     * @param filePath
     * @return
     */
    public static Map<String, Object> loadMapFromClasspath(String filePath) {
        String content = FileUtils.loadTextFromClasspath(filePath);
        return JSONObject.parseObject(content, HashMap.class);
    }

    /**
     * 从类路径加载JSON文件并反序列化成Map
     *
     * @param filePath
     * @return
     */
    public static List<Map<String, Object>> loadListFromClasspath(String filePath) {
        String content = FileUtils.loadTextFromClasspath(filePath);
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
            Map<String, Object> dataMap = JsonUtils.toMap(arrayValue.getJSONObject(i));
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
                value = JsonUtils.toMapList(arrayValue);
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
    public static boolean isAssignableFromJSONObject(JSONObject source, JSONObject target) {
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
            if (!JsonUtils.isAssignableFromObject(svi, tvi)) {
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
    public static boolean isAssignableFromObject(Object source, Object target) {
        if (source == null) {
            return true;
        }

        if (target == null) {
            return false;
        }

        // 只要有一个是基础类型就直接比较
        if (ObjectUtils.isGeneralValue(source) || ObjectUtils.isGeneralValue(target)) {
            return source.equals(target);
        }

        // 两个都是对象类型时
        Object sourceJson = JSON.parse(JSON.toJSONString(source));
        Object targetJson = JSON.parse(JSON.toJSONString(target));
        if (sourceJson instanceof JSONObject && targetJson instanceof JSONObject) {
            return JsonUtils.isAssignableFromJSONObject((JSONObject) sourceJson, (JSONObject) targetJson);
        } else if (sourceJson instanceof JSONArray && targetJson instanceof JSONArray) {
            return JsonUtils.isAssignableFromJSONArray((JSONArray) sourceJson, (JSONArray) targetJson);
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
    public static boolean isAssignableFromJSONArray(JSONArray source, JSONArray target) {
        return JsonUtils.isAssignableFromList(source, target);
    }

    /**
     * 判断源对象中的属性是否在目标对象中都存在，并且值相等
     *
     * @param source
     * @param target
     * @return
     */
    public static <S extends Object, T extends Object> boolean isAssignableFromList(List<S> source, List<T> target) {
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
            if (!JsonUtils.isAssignableFromObject(svi, tvi)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 将一个字符串解析为Java对象
     *
     * @param content
     * @param classType
     * @return
     */
    public static <T extends Object> T parseObject(String content, Class<T> classType) {
        return JSONObject.parseObject(content, classType);
    }

}
