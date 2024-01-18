package com.cmbchina.codefriend.commons.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String content = FileUtils.loadTextFromLocalPath(filePath);
        return JSONObject.parseObject(content, HashMap.class);
    }

    /**
     * 从类路径加载JSON文件并反序列化成Map
     *
     * @param filePath
     * @return
     */
    public static List<Map<String, Object>> loadListFromClasspath(String filePath) {
        String content = FileUtils.loadTextFromLocalPath(filePath);
        JSONArray jsonArray = JSONArray.parseArray(content);
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0, l = jsonArray.size(); i < l; i++) {
            String currContent = JSONObject.toJSONString(jsonArray.get(i));
            list.add(JSONObject.parseObject(currContent, HashMap.class));
        }

        return list;
    }
}
