package io.github.cloudstars.lowcode.commons.lang.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * X配置工具类
 *
 * @author clouds
 */
public final class XConfigUtils {

    private XConfigUtils() {
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
     * @param configsArray
     * @param configClass
     * @param <T>
     * @return
     */
    public static <T extends XConfig> List<T> toList(JsonArray configsArray, Class<T> configClass) {
        List<T> configs = new ArrayList<>();
        Constructor<T> constructor = null;
        try {
            constructor = configClass.getConstructor(JsonObject.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("类：" + configClass.getName() + "必须包含参数为JsonObject的构造函数");
        }

        for (Object configItem : configsArray) {
            T config = null;
            try {
                config = constructor.newInstance(configItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            configs.add(config);
        }

        return configs;
    }

}
