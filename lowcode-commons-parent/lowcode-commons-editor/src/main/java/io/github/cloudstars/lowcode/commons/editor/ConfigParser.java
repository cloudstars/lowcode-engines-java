package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.commons.lang.util.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.util.JsonUtils;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;

import java.util.List;

public class ConfigParser {

    /**
     * 从JSON字符串解析配置
     *
     * @param descriptor 规范描述器
     * @param jsonString JSON字符串配置
     * @return 配置对象
     * @param <T> 配置对象的类型
     */
    public static <T extends XConfig> T fromJsonString(XDescriptor descriptor, String jsonString) {
        JsonObject configJson = JsonUtils.parseObject(jsonString);

        return null;

    }

    /**
     * 将一个配置对象转为JSON字符串
     *
     * @param descriptor 规范描述器
     * @param config 配置对象
     * @return JSON字符串
     * @param <T> 配置对象的类型
     */
    public static <T extends XConfig> String toJsonString(XDescriptor descriptor, T config) {
        assert (config.getClass().getName().equals(descriptor.getConfigClassName()));

        List<XDescriptor.Attribute> attributes = descriptor.getAttributes();
        for (XDescriptor.Attribute attribute : attributes) {
            ObjectUtils.getFieldValue(config, attribute.name);
        }
        return null;
    }

    /*
    private static <T extends XConfig> T newConfig() {
        T config = null;
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            config = constructor.newInstance();
        } catch (Exception e) {
            String className = tClass.getName();
            throw new RuntimeException("类" + className + "必须包含一个public的无参构造函数");
        }
    }*/
}
