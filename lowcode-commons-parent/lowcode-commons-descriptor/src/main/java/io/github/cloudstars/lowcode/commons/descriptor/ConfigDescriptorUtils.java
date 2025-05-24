package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.commons.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;

import java.util.List;

/**
 * 配置构建器
 *
 * @author clouds
 */
public final class ConfigDescriptorUtils {

    /**
     * 从Json对象构建配置
     *
     * @param descriptor 配置规范
     * @param configJson
     * @return 配置
     * @param <T> 配置类型
     */
    public static <T extends XConfig> T build(XConfigDescriptor<T> descriptor, JsonObject configJson) {
        Object configInstance = ObjectUtils.newInstance(descriptor.getConfigClass());
        if (!(configInstance instanceof XConfig)) {
            throw new RuntimeException("规范" + descriptor + "中包含的配置类名必须实现XConfig");
        }

        T config = (T) configInstance;
        List<XConfigAttribute> attributes = descriptor.getAttributes();
        for (XConfigAttribute attribute : attributes) {
            attribute.fromJson(configJson, config);
        }

        return config;
    }

    /**
     * 将配置转为Json对象
     *
     * @param descriptor 配置规范
     * @param config 配置
     * @return Json对象
     * @param <T> 配置类型
     */
    public static <T extends XConfig> JsonObject toJson(XConfigDescriptor<T> descriptor, T config) {
        JsonObject configJson = new JsonObject();
        List<XConfigAttribute> attributes = descriptor.getAttributes();
        for (XConfigAttribute attribute : attributes) {
            attribute.toJson(config, configJson);
        }

        return configJson;
    }

}
