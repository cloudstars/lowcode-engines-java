package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.lang.exception.SystemException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 配置工具类
 *
 * @author clouds
 */
public final class ConfigUtils {

    private ConfigUtils() {
    }

    /**
     * 获取配置中指定键对应的值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return Object值
     */
    public static Object get(JsonObject configJson, String key) {
        return configJson.get(key);
    }

    /**
     * 获取配置中指定键对应的不为Null的值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return 不为Null的Object值
     */
    public static Object getRequired(JsonObject configJson, String key) {
        Object value = get(configJson, key);
        checkNonNull(configJson, key, value);
        return value;
    }

    /**
     * 获取配置中指定键对应的String值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return String值
     */
    public static String getString(JsonObject configJson, String key) {
        return (String) get(configJson, key);
    }

    /**
     * 获取配置中指定键对应的不为Null的String值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return 不为Null的String值
     */
    public static String getRequiredString(JsonObject configJson, String key) {
        String value = getString(configJson, key);
        checkNonNull(configJson, key, value);
        return value;
    }

    /**
     * 获取配置中指定键对应的Integer值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return Integer值
     */
    public static Integer getInteger(JsonObject configJson, String key) {
        return (Integer) get(configJson, key);
    }

    /**
     * 获取配置中指定键对应的Integer值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return 不为Null的Integer值
     */
    public static Integer getRequiredInteger(JsonObject configJson, String key) {
        Integer value = getInteger(configJson, key);
        checkNonNull(configJson, key, value);
        return value;
    }

    /**
     * 获取配置中指定键对应的Boolean值
     *
     * @param configJson 配置对象
     * @param key        键* @return String值
     */
    public static Boolean getBoolean(JsonObject configJson, String key) {
        return (Boolean) get(configJson, key);
    }


    /**
     * 获取配置中指定键对应的不为Null的Boolean值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return 不为Null的String值
     */
    public static Boolean getRequiredBoolean(JsonObject configJson, String key) {
        Boolean value = getBoolean(configJson, key);
        checkNonNull(configJson, key, value);
        return value;
    }


    /**
     * 获取配置中指定键对应的枚举值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return 枚举值
     */
    public static <T extends Enum> T getEnum(JsonObject configJson, String key, Class<T> enumClass) {
        String name = getString(configJson, key);
        return (T) Enum.valueOf(enumClass, name);
    }

    /**
     * 获取配置中指定键对应的不为Null的枚举值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return 枚举值
     */
    public static <T extends Enum> T getRequiredEnum(JsonObject configJson, String key, Class<T> enumClass) {
        T value = getEnum(configJson, key, enumClass);
        checkNonNull(configJson, key, value);
        return value;
    }

    /**
     * 获取配置中指定键对应的JsonObject值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return JsonArray值
     */
    public static JsonObject getJsonObject(JsonObject configJson, String key) {
        return (JsonObject) get(configJson, key);
    }

    /**
     * 获取配置中指定键对应的不为Null的JsonObject值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return JsonArray值
     */
    public static JsonObject getRequiredJsonObject(JsonObject configJson, String key) {
        JsonObject value = getJsonObject(configJson, key);
        checkNonNull(configJson, key, value);
        return value;
    }

    /**
     * 获取配置中指定键对应的JsonArray值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return JsonArray值
     */
    public static JsonArray getJsonArray(JsonObject configJson, String key) {
        return (JsonArray) get(configJson, key);
    }

    /**
     * 获取配置中指定键对应的不为Null的JsonArray值
     *
     * @param configJson 配置对象
     * @param key        键
     * @return JsonArray值
     */
    public static JsonArray getRequiredJsonArray(JsonObject configJson, String key) {
        JsonArray value = getJsonArray(configJson, key);
        checkNonNull(configJson, key, value);
        return value;
    }

    /**
     * 获取配置中指定键对应的Object值
     *
     * @param configJson
     * @param key
     * @param function
     * @param <T>
     * @return 对象值
     */
    public static <T extends XConfig> T getObject(JsonObject configJson, String key, Function<JsonObject, T> function) {
        JsonObject valueObject = (JsonObject) configJson.get(key);
        T config = null;
        if (valueObject != null) {
            config = function.apply(valueObject);
        }

        return config;
    }

    /**
     * 获取配置中指定键对应的不为Null的Object值
     *
     * @param configJson
     * @param key
     * @param function
     * @param <T>
     * @return 对象值
     */
    public static <T extends XConfig> T getRequiredObject(JsonObject configJson, String key, Function<JsonObject, T> function) {
        JsonObject valueObject = (JsonObject) configJson.get(key);
        checkNonNull(configJson, key, valueObject);
        return function.apply(valueObject);
    }

    /**
     * 获取配置中指定键对应的List值
     *
     * @param configJson
     * @param key
     * @param function
     * @param <T>
     * @return 列表值
     */
    public static <T extends XConfig> List<T> getList(JsonObject configJson, String key, Function<JsonObject, T> function) {
        JsonArray valueArray = (JsonArray) configJson.get(key);
        List<T> configList = null;
        if (valueArray != null) {
            configList = new ArrayList<>();
            for (Object valueItem : valueArray) {
                T config = function.apply((JsonObject) valueItem);
                configList.add(config);
            }
        }

        return configList;
    }

    /**
     * 获取配置中指定键对应的不为Null的List值
     *
     * @param configJson
     * @param key
     * @param function
     * @param <T>
     * @return 列表值
     */
    public static <T extends XConfig> List<T> getRequiredList(JsonObject configJson, String key, Function<JsonObject, T> function) {
        JsonArray valueArray = (JsonArray) configJson.get(key);
        checkNonNull(configJson, key, valueArray);
        List<T> configList = new ArrayList<>();
        for (Object valueItem : valueArray) {
            T config = function.apply((JsonObject) valueItem);
            configList.add(config);
        }

        return configList;
    }

    /**
     * 校验配置中指定键对应的值不为Null
     *
     * @param configJson 配置对象
     * @param key        键
     * @param value      Object值
     */
    public static Object checkNonNull(JsonObject configJson, String key, Object value) {
        if (value == null) {
            throw new SystemException("配置下的属性[" + key + "]不能为空：" + configJson.toJsonString());
        }

        return value;
    }

    /**
     * 取值并消费
     *
     * @param configJson 配置对象
     * @param key        键
     * @param consumer   消费者
     */
    public static <T extends Object> void consume(JsonObject configJson, String key, Consumer<T> consumer) {
        consumer.accept((T) configJson.get(key));
    }

    /**
     * 当配置中存在键时，取值并消费
     *
     * @param configJson 配置对象
     * @param key        键
     * @param consumer   消费者
     */
    public static <T extends Object> void consumeIfPresent(JsonObject configJson, String key, Consumer<T> consumer) {
        if (configJson.containsKey(key)) {
            consumer.accept((T) configJson.get(key));
        }
    }

    /**
     * 值不为空时添加
     *
     * @param configJson JSON配置
     * @param key        配置KEY
     * @param value      配置值
     */
    public static void put(JsonObject configJson, String key, Object value) {
        if (value != null) {
            configJson.put(key, value);
        }
    }

    /**
     * 添加配置
     *
     * @param configJson JSON配置
     * @param key        配置KEY
     * @param value      配置值
     */
    public static void putRequired(JsonObject configJson, String key, Object value) {
        checkNonNull(configJson, key, value);
        put(configJson, key, value);
    }

    /**
     * 添加一个非必填的JSON
     *
     * @param configJson JSON配置
     * @param key        配置KEY
     * @param value      配置值
     */
    public static <T extends XConfig> void putJsonObject(JsonObject configJson, String key, T value) {
        if (value != null) {
            configJson.put(key, value.toJson());
        }
    }

    /**
     * 添加配置的Json
     *
     * @param configJson JSON配置
     * @param key        配置KEY
     * @param value      配置值
     */
    public static <T extends XConfig> void putRequiredJsonObject(JsonObject configJson, String key, T value) {
        checkNonNull(configJson, key, value);
        putJsonObject(configJson, key, value);
    }

    /**
     * 添加一个非必填的列表
     *
     * @param configJson
     * @param key
     * @param values
     * @param <T>
     */
    public static <T extends XConfig> void putJsonArray(JsonObject configJson, String key, List<T> values) {
        if (values != null) {
            configJson.put(key, toJsonArray(values));
        }
    }

    /**
     * 添加一个列表
     *
     * @param configJson 目标的JSON配置
     * @param key        配置的键
     * @param values     配置的列表
     * @param <T>        配置子项的类型
     */
    public static <T extends XConfig> void putRequiredJsonArray(JsonObject configJson, String key, List<T> values) {
        checkNonNull(configJson, key, values);
        putJsonArray(configJson, key, values);
    }

    /**
     * 添加一个配置列表
     *
     * @param configJson 目标的JSON配置
     * @param key        配置的键
     * @param values     配置的列表
     * @param function   配置处理函数，接受配置并返回Json对象
     * @param <T>        配置子项的类型
     */
    public static <T extends XConfig> void putList(JsonObject configJson, String key, List<T> values, Function<T, JsonObject> function) {
        if (values != null) {
            List<JsonObject> valuesConfigJson = new ArrayList<>();
            for (T value : values) {
                JsonObject valueConfigJson = function.apply(value);
                valuesConfigJson.add(valueConfigJson);
            }
            configJson.put(key, valuesConfigJson);
        }
    }

    /**
     * 添加一个配置列表
     *
     * @param configJson 目标的JSON配置
     * @param key        配置的键
     * @param values     配置的列表
     * @param function   配置处理函数，接受配置并返回Json对象
     * @param <T>        配置子项的类型
     */
    public static <T extends XConfig> void putRequiredList(JsonObject configJson, String key, List<T> values, Function<T, JsonObject> function) {
        checkNonNull(configJson, key, values);
        putList(configJson, key, values, function);
    }

    /**
     * 添加来源配置中的全部属性
     *
     * @param configJson
     * @param fromConfig
     */
    public static void putAll(JsonObject configJson, XConfig fromConfig) {
        if (fromConfig != null) {
            configJson.putAll(fromConfig.toJson());
        }
    }

    /**
     * 添加来源配置中的全部属性（如果来源配置不为null的话）
     *
     * @param configJson
     * @param fromConfig
     */
    public static void putAllRequired(JsonObject configJson, XConfig fromConfig) {
        if (fromConfig == null) {
            throw new SystemException("不能将null配置添加到配置中：" + configJson.toJsonString());
        }
        putAll(configJson, fromConfig);
    }

    /**
     * 添加来源配置中的全部属性（同名属性忽略）
     *
     * @param configJson
     * @param fromConfig
     */
    public static void putAllIgnorePresent(JsonObject configJson, XConfig fromConfig) {
        JsonObject<String, Object> fromConfigJson = fromConfig.toJson();
        fromConfigJson.forEach((k, v) -> {
            if (!configJson.containsKey(k)) {
                configJson.put(k, v);
            }
        });
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
    /* @Deprecated 请使用 getList */
    /*public static <T extends XConfig> List<T> toList(JsonArray configsJson, Class<T> configClass) {
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
    }*/

    /**
     * 将配置列表转换为JsonArray
     *
     * @param jsonArray 配置列表
     * @param <T>       配置类型
     * @return JsonArray
     */
    // @Deprecated/* 请使用getList */
    /*public static <T extends XConfig> List<T> fromJsonArray(JsonArray jsonArray, Function<JsonObject, T> function) {
        List<T> configs = new ArrayList<>();
        jsonArray.forEach(jsonItem -> configs.add(function.apply((JsonObject) jsonItem)));

        return configs;
    }*/

}
