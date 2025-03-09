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
     * 注册一个注册器
     *
     * @param descriptor
     */
    public static void register(XDescriptor descriptor) {
        DESCRIPTORS.put(descriptor.getName(), descriptor);
    }

}
