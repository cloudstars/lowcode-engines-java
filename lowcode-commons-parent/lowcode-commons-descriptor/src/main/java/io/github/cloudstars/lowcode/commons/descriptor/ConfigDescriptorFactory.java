package io.github.cloudstars.lowcode.commons.descriptor;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册器工厂
 */
public final class ConfigDescriptorFactory {

    /**
     * 注册器映射
     */
    static final Map<String, XConfigDescriptor> DESCRIPTORS = new HashMap<>();

    /**
     * 注册一个规范注册器
     *
     * @param descriptor
     */
    public static void register(XConfigDescriptor descriptor) {
        DESCRIPTORS.put(descriptor.getName(), descriptor);
    }

    /**
     * 获取一个规范描述器
     *
     * @param descriptorName
     * @return 规范描述器
     */
    public static XConfigDescriptor get(String descriptorName) {
        XConfigDescriptor descriptor = DESCRIPTORS.get(descriptorName);
        if (descriptor == null) {
            throw new RuntimeException("配置规范" + descriptorName + "不存在，请检查!");
        }
        return descriptor;
    }

}
