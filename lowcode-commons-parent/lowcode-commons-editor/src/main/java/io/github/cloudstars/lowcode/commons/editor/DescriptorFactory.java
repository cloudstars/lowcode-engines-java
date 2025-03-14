package io.github.cloudstars.lowcode.commons.editor;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册器工厂
 */
public final class DescriptorFactory {

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
        assert (descriptor != null);

        return descriptor;
    }

}
