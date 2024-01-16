package com.cmbchina.codefriend.commons.test.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * JSON 工具类
 *
 * @author 80274507
 */
public final class JsonUtils {
    /**
     * 从类路径下加载JSON对象
     *
     * @param fileName
     * @return
     */
    public static Map<String, Object> loadMapFromClasspath(String fileName) {
        ClassPathResource resource = new ClassPathResource(fileName);
        try {
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            JSONObject jsonData = JSONObject.parseObject(new String(data, StandardCharsets.UTF_8));
            return parseJsonObject(jsonData);
        } catch (IOException e) {
            throw new RuntimeException("文件" + fileName + "加载失败！");
        }
    }
    /**
     * 从类路径下加载JSON对象列表
     *
     * @param fileName
     * @return
     */
    public static List<Map<String, Object>> loadMapListFromClasspath(String fileName) {
        ClassPathResource resource = new ClassPathResource(fileName);
        try {
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            JSONArray jsonArrayData = JSONArray.parseArray(new String(data, StandardCharsets.UTF_8));
            return parseJsonArray(jsonArrayData);
        } catch (IOException e) {
            throw new RuntimeException("文件" + fileName + "加载失败！");
        }
    }

    /**
     * 解析JSON数组
     *
     * @param arrayValue
     * @return
     */
    private static List<Map<String, Object>> parseJsonArray(JSONArray arrayValue) {
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        for (int i = 0; i < arrayValue.size(); i++) {
            Map<String, Object> dataMap = parseJsonObject(arrayValue.getJSONObject(i));
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
    private static Map<String, Object> parseJsonObject(JSONObject jsonData) {
        Map<String, Object> dataMap = new HashMap<>();
        for (String key : jsonData.keySet()) {
            Object value = jsonData.get(key);
            if (value instanceof JSONArray) {
                JSONArray arrayValue = (JSONArray) value;
                value = parseJsonArray(arrayValue);
            } else if (value instanceof JSONObject) {
                value = value.toString();
            }
            dataMap.put(key, value);
        }

        return dataMap;
    }


    /**
     * 加载JSON文本
     *
     * @param fileName 类路径下的json文件
     * @return
     */
    public static String loadJsonString(String fileName) {
        return getJson(fileName, StandardCharsets.UTF_8);
    }

    /**
     * 加载JSON文本
     *
     * @param classpathFileName
     * @param charset
     * @return
     */
    public static String getJson(String classpathFileName, Charset charset) {
        if (classpathFileName == null) {
            return null;
        } else {
            if (classpathFileName.endsWith(".json")) {
                return getJson(new ClassPathResource(classpathFileName), charset);
            } else {
                return classpathFileName;
            }
        }
    }

    private static String getJson(Resource source, Charset charset) {
        try {
            return getJson(source.getInputStream(), charset);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load JSON from " + source, e);
        }
    }

    private static String getJson(InputStream source, Charset charset) {
        try {
            return FileCopyUtils.copyToString(new InputStreamReader(source, charset));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load JSON from InputStream", e);
        }
    }

    /**
     * 从类路径下加载JSON对象
     *
     * @param fileName
     * @return
     */
    public static JSONObject loadJsonObjectFromClasspath(String fileName) {
        ClassPathResource resource = new ClassPathResource(fileName);
        try {
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return JSONObject.parseObject(new String(data, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("文件" + fileName + "加载失败！");
        }
    }

    /**
     * 从类路径下加载JSON数据
     *
     * @param fileName
     * @return
     */
    public static JSONArray loadJsonArrayFromClasspath(String fileName) {
        ClassPathResource resource = new ClassPathResource(fileName);
        try {
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return JSONArray.parseArray(new String(data, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("文件" + fileName + "加载失败！");
        }
    }

    /**
     * 判断一个Map列表的数据是否等于一个JSON数据
     *
     * @param dataList
     * @param array
     * @return
     */
    public static boolean equals(List<Map<String, Object>> dataList, JSONArray array) {
        if (dataList == null && array == null) {
            return true;
        }

        if (dataList == null || array == null) {
            return false;
        }

        int dataSize = dataList.size();
        if (dataSize != array.size()) {
            return false;
        }

        for (int i = 0; i < dataSize; i++) {
            if (!equals(dataList.get(i), array.getJSONObject(i))) {
                return false;
            }
        }


        return true;
    }

    /**
     * 判断一个Map的数据是否等于一个JSON对象的数据
     *
     * @param dataMap
     * @param object
     * @return
     */
    public static boolean equals(Map<String, Object> dataMap, JSONObject object) {
        if (dataMap == null && object == null) {
            return true;
        }

        if (dataMap == null || object == null) {
            return false;
        }

        if (dataMap.size() != object.size()) {
            return false;
        }

        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            Object dataValue = entry.getValue();
            Object objectValue = object.get(entry.getKey());
            if (dataValue instanceof List && objectValue instanceof JSONArray) {
                if (!equals((List<Map<String, Object>>) dataValue, (JSONArray) objectValue)) {
                    return false;
                }
            } else if (!equals(dataValue, objectValue)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断两个数值是否相等
     *
     * @param dataValue
     * @param objectValue
     * @return
     */
    private static boolean equals(Object dataValue, Object objectValue) {
        if (dataValue == null && objectValue == null) {
            return true;
        }

        if (dataValue != null) {
            // 作日期作特殊处理
            if (dataValue instanceof Date && objectValue instanceof Long) {
                return ((Date) dataValue).getTime() == (Long) objectValue;
            } else if (dataValue instanceof Long && objectValue instanceof Date) {
                return (Long) dataValue == ((Date) objectValue).getTime();
            } else if (dataValue instanceof Date && objectValue instanceof String) {
                return dataValue.toString().equals(objectValue);
            }

            return dataValue.equals(objectValue);
        }

        return false;
    }

}
