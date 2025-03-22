package io.github.cloudstars.lowcode.commons.lang.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册器工厂
 */
public final class XDescriptorFactory {

    /**
     * 注册器映射
     */
    final static Map<String, XDescriptor> DESCRIPTORS = new HashMap<>();

    /**
     * 注册一个规范注册器
     *
     * @param descriptor
     */
    public static void register(XDescriptor descriptor) {
        DESCRIPTORS.put(descriptor.getName(), descriptor);
    }

    /**
     * 获取一个规范描述器
     *
     * @param descriptorName
     * @return 规范描述器
     */
    public static XDescriptor get(String descriptorName) {
        XDescriptor descriptor = DESCRIPTORS.get(descriptorName);
        if (descriptor == null) {
            throw new RuntimeException("配置规范" + descriptorName + "不存在，请检查!");
        }
        return descriptor;
    }

}
