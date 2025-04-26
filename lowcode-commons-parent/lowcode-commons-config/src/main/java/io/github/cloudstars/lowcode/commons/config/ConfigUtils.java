package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * X配置工具类
 *
 * @author clouds
 */
public final class ConfigUtils {

    private ConfigUtils() {
    }


    /**
     * 值不为空时添加
     *
     * @param key
     * @param value
     */
    public static void putIfNotNull(JsonObject configJson, String key, Object value) {
        if (value != null) {
            configJson.put(key, value);
        }
    }

    /**
     * 添加配置的Json
     *
     * @param configJson JSON配置
     * @param key        配置KEY
     * @param value      配置值
     */
    public static void putJson(JsonObject configJson, String key, XConfig value) {
        configJson.put(key, value.toJson());
    }

    /**
     * 值不为空时添加配置的Json
     *
     * @param configJson JSON配置
     * @param key        配置KEY
     * @param value      配置值
     */
    public static void putJsonIfNotNull(JsonObject configJson, String key, XConfig value) {
        if (value != null) {
            configJson.put(key, value.toJson());
        }
    }

    /**
     * 添加一个配置列表
     *
     * @param configJson 目标的JSON配置
     * @param key 配置的键
     * @param values 配置的列表
     * @param <T> 配置子项的类型
     */
    public static <T extends XConfig> void putArray(JsonObject configJson, String key, List<T> values) {
        configJson.put(key, toJsonArray(values));
    }

    public static <T extends XConfig> void putArrayIfNotNull(JsonObject configJson, String key, List<T> values) {
        if (values != null) {
            configJson.put(key, toJsonArray(values));
        }
    }

    /**
     * 将配置列表转为JSON数组
     *
     * @param configs
     * @return
     */
    public static <T extends XConfig> JsonArray toJsonArray(List<T> configs) {
        JsonArray jsonArray = new JsonArray();
        for (XConfig config : configs) {
            jsonArray.add(config.toJson());
        }
        return jsonArray;
    }

    /**
     * 将JSON数组配置转为对为的配置列表
     *
     * @param configsJson
     * @param configClass
     * @param <T>
     * @return
     */
    public static <T extends XConfig> List<T> toList(JsonArray configsJson, Class<T> configClass) {
        List<T> configs = new ArrayList<>();
        Constructor<T> constructor = null;
        try {
            constructor = configClass.getConstructor(JsonObject.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("类：" + configClass.getName() + "必须包含参数为JsonObject的构造函数");
        }

        for (Object configJson : configsJson) {
            T config = null;
            try {
                config = constructor.newInstance(configJson);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            configs.add(config);
        }

        return configs;
    }

    /**
     * 将配置列表转换为JsonArray
     *
     * @param jsonArray 配置列表
     * @param <T>       配置类型
     * @return JsonArray
     */
    public static <T extends XConfig> List<T> fromJsonArray(JsonArray jsonArray, Function<JsonObject, T> function) {
        List<T> configs = new ArrayList<>();
        jsonArray.forEach(jsonItem -> configs.add(function.apply((JsonObject) jsonItem)));

        return configs;
    }

}
