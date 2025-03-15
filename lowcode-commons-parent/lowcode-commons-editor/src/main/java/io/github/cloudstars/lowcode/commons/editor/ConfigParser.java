package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.commons.editor.XDescriptor.Attribute;
import io.github.cloudstars.lowcode.commons.lang.value.ObjectValueUtils;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;
import io.github.cloudstars.lowcode.commons.utils.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.utils.object.ObjectUtils;

import java.util.List;

/**
 * 配置解析器
 *
 * @author clouds
 */
public class ConfigParser {

    /**
     * 从JSON字符串解析配置
     *
     * @param jsonString JSON字符串配置
     * @return 配置对象
     * @param <T> 配置对象的类型
     */
    public static <T extends XConfig> T fromJsonString(String jsonString) {
        JsonObject configJson = JsonUtils.parseObject(jsonString);
        Object descriptorValue = configJson.get("descriptor");
        if (descriptorValue == null) {
            throw new RuntimeException("JSON配置中必须包含descriptor规范说明的属性");
        }
        XDescriptor descriptor = DescriptorFactory.get(descriptorValue.toString());
        Object configInstance = ObjectUtils.newInstance(descriptor.getConfigClassName());
        if (!(configInstance instanceof XConfig)) {
            throw new RuntimeException("规范" + descriptor + "中包含的配置类名必须实现XConfig");
        }
        T config = (T) configInstance;

        List<Attribute> attributes = descriptor.getAttributes();
        for (Attribute attribute : attributes) {
            String attrName = attribute.name;
            Object attrValue = configJson.get(attrName);
            ObjectValueUtils.setFieldValue(config, attrName, attrValue);
        }

        return config;
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

        List<Attribute> attributes = descriptor.getAttributes();
        for (Attribute attribute : attributes) {
            ObjectValueUtils.getFieldValue(config, attribute.name);
        }
        return null;
    }



}
